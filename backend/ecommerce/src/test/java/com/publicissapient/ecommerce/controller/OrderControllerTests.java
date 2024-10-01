package com.publicissapient.ecommerce.controller;


import com.publicissapient.ecommerce.service.impl.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;

public class OrderControllerTests {
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void testCreateOrder() {
        Long userId = 1L;
        List<Long> productIds = Arrays.asList(1L, 2L);
        doNothing().when(orderService).createOrder(anyLong(), anyLong());
        orderController.createOrder(userId, productIds);
        for (Long productId : productIds) {
            orderService.createOrder(userId, productId);
        }
    }
}
