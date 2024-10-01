package com.publicissapient.ecommerce.entities;

import com.publicissapient.ecommerce.dto.OrderDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDTOTest {

    @Test
    public void testOrderDTOConstructor() {
        // Create Order object
        Order order = new Order();
        order.setOrderId(1L);
        order.setUserId(100L);
        order.setProductId(200L);
        order.setCreatedAt(LocalDate.now());

        // Create Product object
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(99.99);

        // Create OrderDTO object using the constructor
        OrderDTO orderDTO = new OrderDTO(order, product);

        // Assert expected values
        assertEquals(order.getOrderId(), orderDTO.getOrderId());
        assertEquals(order.getUserId(), orderDTO.getUserId());
        assertEquals(order.getProductId(), orderDTO.getProductId());
        assertEquals(order.getCreatedAt(), orderDTO.getCreatedAt());

        assertEquals(product.getName(), orderDTO.getProductName());
        assertEquals(product.getDescription(), orderDTO.getProductDescription());
        assertEquals(product.getPrice(), orderDTO.getProductPrice());
    }
}

