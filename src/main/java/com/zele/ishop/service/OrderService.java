package com.zele.ishop.service;

import com.zele.ishop.dto.order.OrderUserViewDto;
import com.zele.ishop.entity.Order;
import com.zele.ishop.entity.OrderItem;
import com.zele.ishop.entity.Role;
import com.zele.ishop.entity.Status;
import com.zele.ishop.mapper.OrderMapper;
import com.zele.ishop.repository.CartItemRepository;
import com.zele.ishop.repository.OrderRepository;
import com.zele.ishop.repository.ProductRepository;
import com.zele.ishop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderUserViewDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderUserViewDto)
                .toList();
    };


    @Transactional
    public ResponseEntity<OrderUserViewDto> addOrderFromCart(Long id) {
       var user = userRepository.findById(id).orElse(null);
       if (user == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
       if (user.getRole().equals(Role.SHOP_OWNER)) {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}
       var cartItems = user.getCartItems();
       if (cartItems.isEmpty()) {return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
       var order = new Order();
       order.setUser(user);
       order.setStatus(Status.PLACED);
       order.setOrderItems(new ArrayList<>());
       double total = 0;

       for (var item : cartItems) {
           var product = item.getProduct();
           product.setQuantity(product.getQuantity() - item.getQuantity());
           productRepository.save(product);

           var orderItem = new OrderItem();
           orderItem.setOrder(order);
           orderItem.setProduct(product);
           orderItem.setQuantity(item.getQuantity());
           orderItem.setPrice(product.getPrice());

           order.getOrderItems().add(orderItem);

           var price = product.getPrice() * item.getQuantity();
           total += price;
       }
       var product = productRepository.findByCartItems(cartItems);
       product.getShopOwner().setAccountBalance(product.getShopOwner().getAccountBalance() + total);
       if (user.getAccountBalance() < total) {return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();}
       user.setAccountBalance(user.getAccountBalance() - total);

       order.setTotalAmount(total);
       userRepository.save(user);
       orderRepository.save(order);
       cartItemRepository.deleteAllByUserId(user.getId());
       return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.toOrderUserViewDto(order));
    }

    public ResponseEntity<Void> deleteOrder(Long userId, Long orderId) {
        var user = userRepository.findById(userId).orElse(null);
        if (user == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (user.getRole().equals(Role.SHOP_OWNER)) {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}
        var order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (!order.getUser().getId().equals(user.getId())) {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}
        orderRepository.delete(order);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
