package com.publicissapient.ecommerce.controller;

import com.publicissapient.ecommerce.entities.Cart;
import com.publicissapient.ecommerce.repository.CartRepository;
import com.publicissapient.ecommerce.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling cart operations.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    /**
     * Retrieves the cart items for a given user.
     *
     * @param userId The ID of the user whose cart items are to be retrieved.
     * @return List of cart items associated with the user.
     */
    @GetMapping("/{userId}")
    public List<Cart> getCartByUserId(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    /**
     * Removes a product from the user's cart.
     *
     * @param userId    The ID of the user whose cart needs to be modified.
     * @param productId The ID of the product to be removed from the cart.
     * @return Success message if product is removed successfully, otherwise error message.
     */
    @DeleteMapping("/{userId}/remove/{productId}")
    public String removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        try {
            Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
            if (cart != null) {
                cartRepository.delete(cart);
            }
            return "Product removed from cart successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a product to the user's cart.
     *
     * @param userId    The ID of the user to whose cart the product will be added.
     * @param cartItem  The cart item object containing product details to be added.
     * @return Success message if product is added successfully, otherwise error message.
     */
    @PostMapping("/add/{userId}")
    public String addToCart(@PathVariable Long userId, @RequestBody Cart cartItem) {
        try {
            cartService.addItemToCart(userId, cartItem);
            return "Product added to cart successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}

