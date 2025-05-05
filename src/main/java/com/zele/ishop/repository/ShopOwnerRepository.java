package com.zele.ishop.repository;

import com.zele.ishop.entity.ShopOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopOwnerRepository extends JpaRepository<ShopOwner, Long> {
    ShopOwner findByEmail(String email);
    ShopOwner findByUsername(String username);
}
