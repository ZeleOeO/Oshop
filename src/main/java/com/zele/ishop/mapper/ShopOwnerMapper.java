package com.zele.ishop.mapper;

import com.zele.ishop.dto.user.UserDto;
import com.zele.ishop.dto.user.UserRegisterRequest;
import com.zele.ishop.dto.user.UserUpdateRequest;
import com.zele.ishop.entity.ShopOwner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShopOwnerMapper {
    UserDto toDto(ShopOwner shopOwner);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "accountBalance", target = "accountBalance")
    ShopOwner toEntity(UserRegisterRequest shopOwnerRequest);

    void update(UserUpdateRequest shopOwnerRequest, @MappingTarget ShopOwner shopOwner);
}
