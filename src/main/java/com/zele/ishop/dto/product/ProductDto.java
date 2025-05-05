package com.zele.ishop.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
   private Byte id;
   private String name;
   private String brand;
   private Double price;
   private int quantity;

   @JsonIgnore
   private Byte categoryId;
   @JsonIgnore
   private Byte shopOwnerId;
}
