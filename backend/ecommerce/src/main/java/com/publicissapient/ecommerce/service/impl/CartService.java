package com.publicissapient.ecommerce.service.impl;

import com.publicissapient.ecommerce.entities.Cart;
import com.publicissapient.ecommerce.entities.Product;
import com.publicissapient.ecommerce.repository.CartRepository;
import com.publicissapient.ecommerce.repository.ProductRepository;
import com.publicissapient.ecommerce.repository.UserInfoRepository;
import com.publicissapient.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    public List<Cart> getCartByUserId(Long userId) {

        return cartRepository.findByUserId(userId);
    }

    public void removeItemFromCart(Long userId, Long cartId) throws Exception {
        Optional<Cart> cartItem = cartRepository.findById(cartId);
        if (!cartItem.isPresent() || !cartItem.get().getUserId().equals(userId)) {
            throw new Exception("Cart item not found or does not belong to the user");
        }

        cartRepository.delete(cartItem.get());
    }

    public Cart addItemToCart(Long userId, Cart cartItem) throws Exception {
       try{ Cart existingCartItem = cartRepository.findByUserIdAndProductId(userId, cartItem.getProductId());

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            existingCartItem.setPrice(existingCartItem.getPrice()+ cartItem.getPrice());
            return cartRepository.save(existingCartItem);
        } else {
            Optional<Product> product = Optional.ofNullable(productService.getProductById(cartItem.getProductId()));
            if (!product.isPresent()) {
                throw new IllegalArgumentException("Invalid productId: " + cartItem.getProductId());
            }
            cartItem.setUserId(userId);
            return cartRepository.save(cartItem);
        }
       }
       catch(Exception e){
           throw new Exception("Failed to add item to cart");
       }
    }
}

