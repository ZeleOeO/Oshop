package com.zele.ishop.mapper;

import com.zele.ishop.dto.product.CreateProductRequest;
import com.zele.ishop.dto.product.ProductDto;
import com.zele.ishop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "shopOwnerId", source = "shopOwner.id")
    ProductDto toDto(Product product);

    Product toEntity(CreateProductRequest createProductRequest);
    void update(CreateProductRequest createProductRequest, @MappingTarget Product product);

}
