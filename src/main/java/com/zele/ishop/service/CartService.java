package com.zele.ishop.service;

import com.zele.ishop.dto.cartItem.CartItemDto;
import com.zele.ishop.entity.CartItem;
import com.zele.ishop.entity.Role;
import com.zele.ishop.mapper.CartItemMapper;
import com.zele.ishop.repository.CartItemRepository;
import com.zele.ishop.repository.ProductRepository;
import com.zele.ishop.repository.UserRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public List<CartItemDto> getCartItems() {
        return cartItemRepository.findAll()
                .stream()
                .map(cartItemMapper::toCartItemDto)
                .toList();
    }

    public ResponseEntity<CartItemDto> addItemToCart(Long userId, Long productId, Integer quantity) {
        var user = userRepository.findById(userId).orElse(null);
        var product = productRepository.findById(productId).orElse(null);
        if (user == null || product == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (user.getRole().equals(Role.SHOP_OWNER)) {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}
        if (cartItemRepository.findByUserAndProduct(user, product) != null) {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}
        var cartItem = new CartItem(user, product, quantity);
        if (cartItem.getQuantity() > product.getQuantity()) {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}
        cartItemRepository.save(cartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemMapper.toCartItemDto(cartItem));
    }

    public ResponseEntity<CartItemDto> removeItemFromCart(Long userId, Long productId) {
        var user = userRepository.findById(userId).orElse(null);
        var product = productRepository.findById(productId).orElse(null);
        if (user == null || product == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        if (user.getRole().equals(Role.SHOP_OWNER)) {return ResponseEntity.status(HttpStatus.FORBIDDEN).build();}
        var cartItem = cartItemRepository.findByUserAndProduct(user, product);
        cartItemRepository.delete(cartItem);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemMapper.toCartItemDto(cartItem));
    }
}
