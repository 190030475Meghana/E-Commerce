package com.publicissapient.ecommerce.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderEntityTest {

    @Test
    void testOrderGettersAndSetters() {
        Order order = new Order();
        order.setOrderId(1L);
        order.setUserId(101L);
        order.setProductId(201L);
        order.setCreatedAt(LocalDate.of(2023, 6, 1));

        assertEquals(1L, order.getOrderId());
        assertEquals(101L, order.getUserId());
        assertEquals(201L, order.getProductId());
        assertEquals(LocalDate.of(2023, 6, 1), order.getCreatedAt());
    }

    @Test
    void testOrderConstructors() {
        LocalDate createdAt = LocalDate.of(2023, 6, 1);
        Order order = new Order(1L, 101L, 201L, createdAt);

        assertEquals(1L, order.getOrderId());
        assertEquals(101L, order.getUserId());
        assertEquals(201L, order.getProductId());
        assertEquals(createdAt, order.getCreatedAt());
    }

    @Test
    void testOrderNoArgsConstructor() {
        Order order = new Order();

        assertNull(order.getOrderId());
        assertNull(order.getUserId());
        assertNull(order.getProductId());
        assertNull(order.getCreatedAt());
    }
}

