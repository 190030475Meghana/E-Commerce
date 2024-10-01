package com.publicissapient.ecommerce.service;

import com.publicissapient.ecommerce.entities.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);

    Product getProductById(Long productId);

    List<Product> getAllProducts();
}
