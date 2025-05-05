package com.zele.ishop.mapper;

import com.zele.ishop.dto.user.UserDto;
import com.zele.ishop.dto.user.UserRegisterRequest;
import com.zele.ishop.dto.user.UserUpdateRequest;
import com.zele.ishop.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    UserDto toDto(Customer customer);
    Customer toEntityFromRequest(UserRegisterRequest registerRequest);
    Customer toUpdate(UserUpdateRequest updateRequest, @MappingTarget Customer customer);
}
