package com.publicissapient.ecommerce.controller;
import com.publicissapient.ecommerce.entities.Product;
import com.publicissapient.ecommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTests {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product(1L, "Product 1", "Description for Product 1", 100.0, null);
        Product product2 = new Product(2L, "Product 2", "Description for Product 2", 200.0, null);
        List<Product> productList = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(productList);

        List<Product> returnedProducts = productController.getAllProducts();

        verify(productService, times(1)).getAllProducts();

        assertEquals(productList.size(), returnedProducts.size());
        assertEquals(productList.get(0), returnedProducts.get(0));
        assertEquals(productList.get(1), returnedProducts.get(1));
    }
}
