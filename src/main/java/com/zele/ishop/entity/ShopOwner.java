package com.zele.ishop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "password")
@Table(name = "shop_owner")
public class ShopOwner {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private Double accountBalance;

    @OneToMany(mappedBy = "shopOwner")
    private List<Product> product;
}
