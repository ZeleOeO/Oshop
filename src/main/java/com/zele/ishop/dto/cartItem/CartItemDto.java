package com.zele.ishop.dto.cartItem;

import lombok.Data;

@Data
public class CartItemDto {
    private String productName;
    private String userName;
    private int quantity;
}
