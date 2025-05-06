package com.zele.ishop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem {
    @Id
    private Long id;
    private int quantity;

    @ManyToOne() @JoinColumn(name = "productId")
    private Product product ;

    @ManyToOne() @JoinColumn(name = "userId")
    private User user;

}
