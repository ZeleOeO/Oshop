package com.zele.ishop.dto.product;

import lombok.Data;

@Data
public class ProductCreateRequest {
    private String name;
    private String brand;
    private Double price;
    private int quantity;
    private String description;
}
