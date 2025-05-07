package com.zele.ishop.controllers;

import com.zele.ishop.dto.order.OrderUserViewDto;
import com.zele.ishop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public List<OrderUserViewDto> getOrderUsers() {
        return orderService.getAllOrders();
    }

    @PostMapping("/new-{id}")
    public ResponseEntity<OrderUserViewDto> addUserOrderFromCart(@PathVariable Long id) {
        return orderService.addOrderFromCart(id);
    }

    @DeleteMapping("/delete-{userId}/{orderId}")
    public ResponseEntity<Void> deleteUserOrder(@PathVariable Long userId, @PathVariable Long orderId) {
        return orderService.deleteOrder(userId, orderId);
    }
}
