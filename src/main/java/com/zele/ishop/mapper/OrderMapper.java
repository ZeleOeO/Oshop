package com.zele.ishop.mapper;

import com.zele.ishop.dto.order.OrderUserViewDto;
import com.zele.ishop.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "orderDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "order.user.name", target = "userName")
    @Mapping(source = "totalAmount", target = "totalAmount", numberFormat = "$#.00")
    OrderUserViewDto toOrderUserViewDto(Order order);
}
