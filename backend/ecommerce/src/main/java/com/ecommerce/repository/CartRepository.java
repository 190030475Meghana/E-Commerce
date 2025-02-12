package com.ecommerce.repository;

import com.ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserIdAndProductId(Long userId, Long foodItemId);
    List<Cart> findByUserId(Long userId);
}
