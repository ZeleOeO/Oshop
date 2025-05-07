package com.zele.ishop.mapper;

import com.zele.ishop.dto.product.ProductCreateRequest;
import com.zele.ishop.dto.product.ProductUpdateRequest;
import com.zele.ishop.dto.product.ProductUserViewDto;
import com.zele.ishop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductUserViewDto productToProductUserViewDto(Product product);

    Product productCreateRequestToProduct(ProductCreateRequest createRequest);

    Product update(ProductUpdateRequest updateRequest, @MappingTarget Product product);
}
