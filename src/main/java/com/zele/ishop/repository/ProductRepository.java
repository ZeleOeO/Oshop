package com.zele.ishop.repository;

import com.zele.ishop.entity.Product;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByShopOwner_IdAndId(Long shopOwnerId, Long id);
    List<Product> findAllByShopOwner_Id(Long shopOwnerId);

    Long id(Long id);
}
