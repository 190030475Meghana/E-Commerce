package com.publicissapient.ecommerce.entities;

import com.publicissapient.ecommerce.entities.CartId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CartIdTest {

    @Test
    void testCartIdGettersAndSetters() {
        CartId cartId = new CartId();
        cartId.setUserId(1L);
        cartId.setProductId(101L);

        assertEquals(1L, cartId.getUserId());
        assertEquals(101L, cartId.getProductId());
    }

    @Test
    void testCartIdAllArgsConstructor() {
        CartId cartId = new CartId(1L, 101L);

        assertEquals(1L, cartId.getUserId());
        assertEquals(101L, cartId.getProductId());
    }

    @Test
    void testCartIdNoArgsConstructor() {
        CartId cartId = new CartId();

        assertNull(cartId.getUserId());
        assertNull(cartId.getProductId());
    }
}

