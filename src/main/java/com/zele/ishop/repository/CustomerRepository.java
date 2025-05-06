package com.zele.ishop.repository;

import com.zele.ishop.entity.Customer;
import com.zele.ishop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByUsername(String name);
//    List<Product>
        @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.cart WHERE c.id = :id")
        Optional<Customer> findByIdWithCart(@Param("id") Long id);
}
