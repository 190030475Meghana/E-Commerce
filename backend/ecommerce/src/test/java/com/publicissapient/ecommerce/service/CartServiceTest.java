package com.publicissapient.ecommerce.service;

import com.publicissapient.ecommerce.entities.Cart;
import com.publicissapient.ecommerce.entities.Category;
import com.publicissapient.ecommerce.entities.Product;
import com.publicissapient.ecommerce.repository.CartRepository;
import com.publicissapient.ecommerce.repository.ProductRepository;
import com.publicissapient.ecommerce.repository.UserInfoRepository;
import com.publicissapient.ecommerce.service.ProductService;
import com.publicissapient.ecommerce.service.impl.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserInfoRepository userInfoRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCartByUserId() {
        Long userId = 1L;
        List<Cart> expectedCartItems = new ArrayList<>();
        // Mocking repository method
        when(cartRepository.findByUserId(userId)).thenReturn(expectedCartItems);

        List<Cart> actualCartItems = cartService.getCartByUserId(userId);

        assertEquals(expectedCartItems, actualCartItems);
    }

    @Test
    public void testRemoveItemFromCart_Success() throws Exception {
        Long userId = 1L;
        Long cartItemId = 10L;
        Cart cartItem = new Cart(userId, 1L, 1, "Product A", "Description A", 100.0);

        when(cartRepository.findById(cartItemId)).thenReturn(Optional.of(cartItem));

        assertDoesNotThrow(() -> cartService.removeItemFromCart(userId, cartItemId));
        verify(cartRepository, times(1)).delete(cartItem);
    }

    @Test
    public void testRemoveItemFromCart_ItemNotFound() {
        Long userId = 1L;
        Long cartItemId = 10L;

        when(cartRepository.findById(cartItemId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> cartService.removeItemFromCart(userId, cartItemId));
        assertEquals("Cart item not found or does not belong to the user", exception.getMessage());
        verify(cartRepository, never()).delete(any());
    }

    @Test
    public void testAddItemToCart_NewItem() throws Exception {
        Long userId = 1L;
        Cart cartItem = new Cart(userId, 1L, 1, "Product A", "Description A", 100.0);
        Category category=new Category(1,"Electronics",null);
        Product product = new Product(1L, "Product A", "Description A", 100.0,category);



        when(cartRepository.findByUserIdAndProductId(userId, cartItem.getProductId())).thenReturn(null);
        when(productService.getProductById(cartItem.getProductId())).thenReturn(product);
        when(cartRepository.save(any(Cart.class))).thenReturn(cartItem);

        Cart addedCartItem = cartService.addItemToCart(userId, cartItem);

        assertNotNull(addedCartItem);
        assertEquals(userId, addedCartItem.getUserId());
        assertEquals(cartItem.getProductId(), addedCartItem.getProductId());
        assertEquals(cartItem.getQuantity(), addedCartItem.getQuantity());
        assertEquals(cartItem.getPrice(), addedCartItem.getPrice());
    }

    @Test
    public void testAddItemToCart_ExistingItem() throws Exception {
        Long userId = 1L;
        Cart cartItem = new Cart(userId, 1L, 1, "Product A", "Description A", 100.0);
        Cart existingCartItem = new Cart(userId, 1L, 2, "Product A", "Description A", 200.0);
        Category category=new Category(1,"Electronics",null);

        Product product = new Product(1L, "Product A", "Description A", 100.0,category);

        when(cartRepository.findByUserIdAndProductId(userId, cartItem.getProductId())).thenReturn(existingCartItem);
        when(productService.getProductById(cartItem.getProductId())).thenReturn(product);
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCartItem);

        Cart addedCartItem = cartService.addItemToCart(userId, cartItem);

        assertNotNull(addedCartItem);
        assertEquals(userId, addedCartItem.getUserId());
        assertEquals(cartItem.getProductId(), addedCartItem.getProductId());
        //assertEquals(existingCartItem.getQuantity() + cartItem.getQuantity(), addedCartItem.getQuantity());
        //assertEquals(existingCartItem.getPrice() + cartItem.getPrice(), addedCartItem.getPrice());
    }

//    @Test
//    public void testAddItemToCart_InvalidProduct() {
//        Long userId = 1L;
//        Cart cartItem = new Cart(userId, 1L, 1, "Product A", "Description A", 100.0);
//
//        when(cartRepository.findByUserIdAndProductId(userId, cartItem.getProductId())).thenReturn(null);
//        when(productService.getProductById(cartItem.getProductId())).thenReturn(null);
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> cartService.addItemToCart(userId, cartItem));
//        assertEquals("Invalid productId: " + cartItem.getProductId(), exception.getMessage());
//        verify(cartRepository, never()).save(any());
//    }

    @Test
    public void testAddItemToCart_Exception() {
        Long userId = 1L;
        Cart cartItem = new Cart(userId, 1L, 1, "Product A", "Description A", 100.0);

        when(cartRepository.findByUserIdAndProductId(userId, cartItem.getProductId())).thenThrow(RuntimeException.class);

        Exception exception = assertThrows(Exception.class, () -> cartService.addItemToCart(userId, cartItem));
        assertEquals("Failed to add item to cart", exception.getMessage());
        verify(cartRepository, never()).save(any());
    }
}
