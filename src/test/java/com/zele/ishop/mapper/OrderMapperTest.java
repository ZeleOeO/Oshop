package com.zele.ishop.mapper;

import com.zele.ishop.dto.order.OrderUserViewDto;
import com.zele.ishop.entity.Order;
import com.zele.ishop.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    OrderMapper mapper;

    @BeforeEach
    void setUp() {
       mapper = new OrderMapperImpl();
    }

    @Test
    void toOrderUserViewDto() {
        User user=new User();
        user.setName("John");
        double amount = 10.0;

        Order order=new Order();
        order.setUser(user);
        order.setTotalAmount(amount);
        OrderUserViewDto dto = mapper.toOrderUserViewDto(order);

        assertNotNull(dto);
        assertEquals(order.getUser().getName(), dto.getUserName());
        assertEquals(amount, Double.toString(dto.getTotalAmount()));
        assertThat(dto.getOrderDate()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(dto.getOrderDate()).isAfter(LocalDateTime.now().minusMinutes(1));

    }
}