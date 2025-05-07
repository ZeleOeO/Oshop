package com.zele.ishop.repository;

import com.zele.ishop.entity.CartItem;
import com.zele.ishop.entity.Product;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCartItems(List<CartItem> cartItems);
}
