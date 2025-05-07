package com.zele.ishop.controllers;

import com.zele.ishop.dto.cartItem.CartItemDto;
import com.zele.ishop.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartItemController {

    private final CartService cartService;

    @GetMapping("/all")
    public List<CartItemDto> getCartItems() {
        return cartService.getCartItems();
    }

    @PostMapping("/new-{userId}/{productId}")
    public ResponseEntity<CartItemDto> createCartItem(@PathVariable Long userId, @PathVariable Long productId, @RequestBody Integer quantity) {
        return cartService.addItemToCart(userId, productId, quantity);
    }

    @DeleteMapping("/delete-{userId}/{productId}")
    public ResponseEntity<CartItemDto> deleteCartItem(@PathVariable Long userId, @PathVariable Long productId) {
        return cartService.removeItemFromCart(userId, productId);
    }
}
