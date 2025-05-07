package com.zele.ishop.repository;

import com.zele.ishop.entity.CartItem;
import com.zele.ishop.entity.Product;
import com.zele.ishop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);
    void deleteAllByUserId(Long userId);
}
