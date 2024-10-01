package com.publicissapient.ecommerce.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartEntityTest {

    @Test
    void testCartGettersAndSetters() {
        Cart cart = new Cart();
        cart.setUserId(1L);
        cart.setProductId(100L);
        cart.setQuantity(2);
        cart.setName("Product A");
        cart.setDescription("Description for Product A");
        cart.setPrice(50.0);

        assertEquals(1L, cart.getUserId());
        assertEquals(100L, cart.getProductId());
        assertEquals(2, cart.getQuantity());
        assertEquals("Product A", cart.getName());
        assertEquals("Description for Product A", cart.getDescription());
        assertEquals(50.0, cart.getPrice());
    }

    @Test
    void testCartConstructors() {
        Cart cart = new Cart(1L, 100L, 2, "Product A", "Description for Product A", 50.0);

        assertEquals(1L, cart.getUserId());
        assertEquals(100L, cart.getProductId());
        assertEquals(2, cart.getQuantity());
        assertEquals("Product A", cart.getName());
        assertEquals("Description for Product A", cart.getDescription());
        assertEquals(50.0, cart.getPrice());
    }
}

