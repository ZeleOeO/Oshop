package com.zele.ishop.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductUpdateRequest {
    private String name;
    private String brand;
    private Double price;
    private int quantity;
    private String description;
}
