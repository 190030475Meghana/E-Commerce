package com.publicissapient.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "carts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@IdClass(CartId.class)
public class Cart {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "productId")
    private Long productId;

    @Column(name="quantity")
    private int quantity;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;


}

