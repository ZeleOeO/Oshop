package com.zele.ishop.dto.product;

import com.zele.ishop.entity.User;
import lombok.Data;

@Data
public class ProductUserViewDto {
    private String name;
    private String brand;
    private Double price;
    private int quantity;
    private String description;
}
