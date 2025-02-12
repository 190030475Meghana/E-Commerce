package com.ecommerce.service;

import com.ecommerce.entities.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);

    Product getProductById(Long productId);

    List<Product> getAllProducts();
}
