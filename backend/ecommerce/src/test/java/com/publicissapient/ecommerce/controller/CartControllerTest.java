package com.publicissapient.ecommerce.controller;

import com.publicissapient.ecommerce.entities.Cart;
import com.publicissapient.ecommerce.repository.CartRepository;
import com.publicissapient.ecommerce.service.impl.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CartControllerTest {
    @Mock
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddToCart() throws Exception {
        // Mock data
        Long userId = 1L;
        Cart cartItem = new Cart(userId, 1L, 1, "Product 1", "Description 1", 10.0);

        // Mock service method
        when(cartService.addItemToCart(userId, cartItem)).thenReturn(cartItem);

        // Call controller method
        String result = cartController.addToCart(userId, cartItem);

        // Assertions
        assertThat(result).isEqualTo("Product added to cart successfully");
        verify(cartService).addItemToCart(userId, cartItem);
    }

    @Test
    public void testGetCartByUserId() {
        // Mock data
        Long userId = 1L;
        List<Cart> mockCartList = new ArrayList<>();
        mockCartList.add(new Cart(userId, 1L, 2, "Product 1", "Description 1", 10.0));

        // Mock service method
        when(cartService.getCartByUserId(userId)).thenReturn(mockCartList);

        // Call controller method
        List<Cart> returnedCartList = cartController.getCartByUserId(userId);

        // Assertions
        assertThat(returnedCartList).isNotNull();
        assertThat(returnedCartList.size()).isEqualTo(mockCartList.size());
        assertThat(returnedCartList.get(0).getUserId()).isEqualTo(userId);
        assertThat(returnedCartList.get(0).getProductId()).isEqualTo(1L);
        // Add more assertions as needed
    }

    @Test
    public void testRemoveFromCart() {
        // Mock data
        Long userId = 1L;
        Long productId = 1L;
        Cart mockCart = new Cart(userId, productId, 2, "Product 1", "Description 1", 10.0);

        // Mock repository method

        when(cartRepository.findByUserIdAndProductId(userId, productId)).thenReturn(mockCart);

        // Call controller method
        String result = cartController.removeFromCart(userId, productId);

        // Assertions
        assertThat(result).isEqualTo("Product removed from cart successfully");
        verify(cartRepository).delete(mockCart);
    }
}
