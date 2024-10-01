package com.publicissapient.ecommerce.service;

import com.publicissapient.ecommerce.dto.OrderDTO;
import com.publicissapient.ecommerce.entities.Order;
import com.publicissapient.ecommerce.entities.Product;
import com.publicissapient.ecommerce.repository.OrderRepository;
import com.publicissapient.ecommerce.service.impl.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        Long userId = 1L;
        Long productId = 1L;
        Order mockOrder = new Order(1L, userId, productId, LocalDate.now());

        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);
        orderService.createOrder(userId, productId);

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testGetOrdersByUserId() {
        Long userId = 1L;
        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(new Order(1L, userId, 1L, LocalDate.now()));
        mockOrders.add(new Order(2L, userId, 2L, LocalDate.now()));

        when(orderRepository.findByUserId(userId)).thenReturn(mockOrders);

        List<Order> retrievedOrders = orderService.getOrdersByUserId(userId);
        verify(orderRepository, times(1)).findByUserId(userId);

        assertEquals(mockOrders.size(), retrievedOrders.size());
        assertEquals(mockOrders.get(0), retrievedOrders.get(0));
        assertEquals(mockOrders.get(1), retrievedOrders.get(1));
    }

    @Test
    void testGetOrdersWithProductDetailsByUserId() {
        Long userId = 1L;
        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(new Order(1L, userId, 1L, LocalDate.now()));
        mockOrders.add(new Order(2L, userId, 2L, LocalDate.now()));

        Product mockProduct1 = new Product(1L, "Product 1", "Description 1", 100.0, null);
        Product mockProduct2 = new Product(2L, "Product 2", "Description 2", 200.0, null);

        when(orderRepository.findByUserId(userId)).thenReturn(mockOrders);

        when(productService.getProductById(1L)).thenReturn(mockProduct1);
        when(productService.getProductById(2L)).thenReturn(mockProduct2);

        List<OrderDTO> orderDTOs = orderService.getOrdersWithProductDetailsByUserId(userId);

        verify(orderRepository, times(1)).findByUserId(userId);

        verify(productService, times(1)).getProductById(1L);
        verify(productService, times(1)).getProductById(2L);

        assertEquals(mockOrders.size(), orderDTOs.size());
        assertEquals(mockOrders.get(0).getUserId(), orderDTOs.get(0).getUserId());
        assertEquals(mockOrders.get(0).getProductId(), orderDTOs.get(0).getProductId());
        assertEquals(mockOrders.get(0).getCreatedAt(), orderDTOs.get(0).getCreatedAt());
        assertEquals(mockProduct1.getName(), orderDTOs.get(0).getProductName());
        assertEquals(mockProduct1.getDescription(), orderDTOs.get(0).getProductDescription());
        assertEquals(mockProduct1.getPrice(), orderDTOs.get(0).getProductPrice());
        assertEquals(mockOrders.get(1).getUserId(), orderDTOs.get(1).getUserId());
        assertEquals(mockOrders.get(1).getProductId(), orderDTOs.get(1).getProductId());
        assertEquals(mockOrders.get(1).getCreatedAt(), orderDTOs.get(1).getCreatedAt());
        assertEquals(mockProduct2.getName(), orderDTOs.get(1).getProductName());
        assertEquals(mockProduct2.getDescription(), orderDTOs.get(1).getProductDescription());
        assertEquals(mockProduct2.getPrice(), orderDTOs.get(1).getProductPrice());
    }
}



