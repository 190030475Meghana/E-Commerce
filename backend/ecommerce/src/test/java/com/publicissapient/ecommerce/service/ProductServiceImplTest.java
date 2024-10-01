package com.publicissapient.ecommerce.service;

import com.publicissapient.ecommerce.entities.Category;
import com.publicissapient.ecommerce.entities.Product;
import com.publicissapient.ecommerce.repository.ProductRepository;
import com.publicissapient.ecommerce.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() {
        Product productToAdd = new Product();
        productToAdd.setName("Test Product");
        productToAdd.setDescription("Testing add product");
        productToAdd.setPrice(100.0);

        when(productRepository.save(any(Product.class))).thenReturn(productToAdd);

        Product addedProduct = productService.addProduct(productToAdd);

          verify(productRepository, times(1)).save(any(Product.class));

        assertNotNull(addedProduct);
        assertEquals("Test Product", addedProduct.getName());
        assertEquals("Testing add product", addedProduct.getDescription());
        assertEquals(100.0, addedProduct.getPrice());
    }

    @Test
    void testGetProductById() {

        Long productId = 1L;
        Product product = new Product();
        product.setProductId(productId);
        product.setName("Test Product");
        product.setDescription("Testing get product by ID");
        product.setPrice(150.0);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product retrievedProduct = productService.getProductById(productId);

        verify(productRepository, times(1)).findById(productId);

        assertNotNull(retrievedProduct);
        assertEquals(productId, retrievedProduct.getProductId());
        assertEquals("Test Product", retrievedProduct.getName());
        assertEquals("Testing get product by ID", retrievedProduct.getDescription());
        assertEquals(150.0, retrievedProduct.getPrice());
    }

    @Test
    void testGetProductById_ProductNotFound() {

        Long productId = 2L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.getProductById(productId));

       verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testGetAllProducts() {

        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description for Product 1");
        product1.setPrice(200.0);

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description for Product 2");
        product2.setPrice(250.0);

        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(product1);
        mockProducts.add(product2);

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> productList = productService.getAllProducts();

        verify(productRepository, times(1)).findAll();

        assertNotNull(productList);
        assertEquals(2, productList.size());

        Product retrievedProduct1 = productList.get(0);
        assertEquals(1L, retrievedProduct1.getProductId());
        assertEquals("Product 1", retrievedProduct1.getName());
        assertEquals("Description for Product 1", retrievedProduct1.getDescription());
        assertEquals(200.0, retrievedProduct1.getPrice());

        Product retrievedProduct2 = productList.get(1);
        assertEquals(2L, retrievedProduct2.getProductId());
        assertEquals("Product 2", retrievedProduct2.getName());
        assertEquals("Description for Product 2", retrievedProduct2.getDescription());
        assertEquals(250.0, retrievedProduct2.getPrice());
    }
}

