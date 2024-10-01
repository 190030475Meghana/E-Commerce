package com.publicissapient.ecommerce.dto;

import com.publicissapient.ecommerce.entities.Order;
import com.publicissapient.ecommerce.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderDTO {
    private Long orderId;
    private Long userId;
    private Long productId;
    private LocalDate createdAt;

    private String productName;
    private String productDescription;
    private double productPrice;

    public OrderDTO(Order order, Product product) {
        this.orderId = order.getOrderId();
        this.userId = order.getUserId();
        this.productId = order.getProductId();
        this.createdAt = order.getCreatedAt();

        this.productName = product.getName();
        this.productDescription = product.getDescription();
        this.productPrice = product.getPrice();
    }
}

