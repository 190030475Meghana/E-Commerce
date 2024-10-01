package com.publicissapient.ecommerce.controller;

import com.publicissapient.ecommerce.dto.OrderDTO;
import com.publicissapient.ecommerce.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create/{userId}")
    public void createOrder(@PathVariable Long userId, @RequestBody List<Long> productIds) {
        for (Long productId : productIds) {
            orderService.createOrder(userId, productId);
        }
    }

    @GetMapping("/{userId}")
    public List<OrderDTO> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersWithProductDetailsByUserId(userId);
    }
}
