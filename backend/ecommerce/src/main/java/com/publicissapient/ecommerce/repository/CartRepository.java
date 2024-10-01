package com.publicissapient.ecommerce.repository;

import com.publicissapient.ecommerce.entities.Cart;
import com.publicissapient.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserIdAndProductId(Long userId, Long foodItemId);
    List<Cart> findByUserId(Long userId);
}
