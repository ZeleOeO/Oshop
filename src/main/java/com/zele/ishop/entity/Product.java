package com.zele.ishop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private Double price;
    private int quantity;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="shop_owner_id")
    private ShopOwner shopOwner;

    @ManyToMany(mappedBy = "cart")
    private Set<Customer> customers;
}
