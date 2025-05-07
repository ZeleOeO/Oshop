package com.zele.ishop.mapper;

import com.zele.ishop.dto.cartItem.CartItemDto;
import com.zele.ishop.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "user.name", target = "userName")
    CartItemDto toCartItemDto(CartItem cartItem);
}
