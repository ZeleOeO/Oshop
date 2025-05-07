package com.zele.ishop.mapper;

import com.zele.ishop.dto.cartItem.CartItemDto;
import com.zele.ishop.entity.CartItem;
import com.zele.ishop.entity.Product;
import com.zele.ishop.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CartItemMapperTest {
    private CartItemMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CartItemMapperImpl();
    }

    @Test
    public void testMap() {
        Product product = new Product();
        product.setName("test");
        User user = new User();
        user.setName("test");
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setUser(user);

        CartItemDto cartItemDto = mapper.toCartItemDto(cartItem);

        assertNotNull(cartItemDto);
        assertEquals(cartItemDto.getUserName(), user.getName());
        assertEquals(cartItemDto.getProductName(), product.getName());
    }
}