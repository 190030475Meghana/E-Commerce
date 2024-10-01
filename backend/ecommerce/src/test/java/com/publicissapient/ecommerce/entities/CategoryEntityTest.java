package com.publicissapient.ecommerce.entities;


import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryEntityTest {

    @Test
    void testCategoryGettersAndSetters() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Electronics");

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId(101L);
        product1.setName("Laptop");
        product1.setDescription("High-performance laptop");
        product1.setPrice(1500.0);
        product1.setCategory(category);

        products.add(product1);

        category.setProducts(products);

        assertEquals(1L, category.getCategoryId());
        assertEquals("Electronics", category.getName());


        assertEquals(1, category.getProducts().size());
        Product retrievedProduct = category.getProducts().get(0);
        assertEquals(101L, retrievedProduct.getProductId());
        assertEquals("Laptop", retrievedProduct.getName());
        assertEquals("High-performance laptop", retrievedProduct.getDescription());
        assertEquals(1500.0, retrievedProduct.getPrice());
        assertEquals(category, retrievedProduct.getCategory());
    }

    @Test
    void testCategoryConstructors() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId(101L);
        product1.setName("Laptop");
        product1.setDescription("High-performance laptop");
        product1.setPrice(1500.0);

        products.add(product1);

        Category category = new Category(1L, "Electronics", products);

        assertEquals(1L, category.getCategoryId());
        assertEquals("Electronics", category.getName());


        assertEquals(1, category.getProducts().size());
        Product retrievedProduct = category.getProducts().get(0);
        assertEquals(101L, retrievedProduct.getProductId());
        assertEquals("Laptop", retrievedProduct.getName());
        assertEquals("High-performance laptop", retrievedProduct.getDescription());
        assertEquals(1500.0, retrievedProduct.getPrice());
        //assertEquals(category, retrievedProduct.getCategory());
    }

    @Test
    public void testAllArgsConstructor() {
        Long productId = 1L;
        String name = "Product A";
        String description = "Description of Product A";
        double price = 100.0;
        Category category = new Category();

        Product product = new Product(productId, name, description, price, category);

        assertEquals(productId, product.getProductId());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(category, product.getCategory());
    }
}

