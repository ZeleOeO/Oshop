package com.zele.ishop.mapper;

import com.zele.ishop.dto.user.RegisterShopOwnerRequest;
import com.zele.ishop.dto.shopowner.ShopOwnerDto;
import com.zele.ishop.dto.user.UserUpdateRequest;
import com.zele.ishop.entity.ShopOwner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShopOwnerMapper {
    ShopOwnerDto toDto(ShopOwner shopOwner);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    ShopOwner toEntity(RegisterShopOwnerRequest shopOwnerRequest);

    void update(UserUpdateRequest shopOwnerRequest, @MappingTarget ShopOwner shopOwner);
}
