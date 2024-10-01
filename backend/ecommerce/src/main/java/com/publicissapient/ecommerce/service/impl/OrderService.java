package com.publicissapient.ecommerce.service.impl;

import com.publicissapient.ecommerce.dto.OrderDTO;
import com.publicissapient.ecommerce.entities.Order;
import com.publicissapient.ecommerce.entities.Product;
import com.publicissapient.ecommerce.repository.OrderRepository;
import com.publicissapient.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public void createOrder(Long userId, Long productId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setCreatedAt(LocalDate.now());

        orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<OrderDTO> getOrdersWithProductDetailsByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this::mapToOrderDTO).collect(Collectors.toList());
    }

    private OrderDTO mapToOrderDTO(Order order) {
        Product product = productService.getProductById(order.getProductId());
        return new OrderDTO(order, product);
    }


}
