package com.publicissapient.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicissapient.ecommerce.entities.Category;
import com.publicissapient.ecommerce.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CategoryControllerTest {
    @Mock
    private CategoryServiceImpl categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCategory() throws Exception {
        // Given
        Category categoryToAdd = new Category(1L, "Electronics", null);
        when(categoryService.addCategory(any(Category.class))).thenReturn(categoryToAdd);

        // When
        ResponseEntity<Category> response = categoryController.addCategory(categoryToAdd);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(categoryToAdd);
    }

    @Test
    public void testGetAllCategoryNames() throws Exception {
        // Given
        Category category1 = new Category(1L, "Electronics", null);
        Category category2 = new Category(2L, "Clothing", null);
        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        // When
        ResponseEntity<List<String>> response = categoryController.getAllCategoryNames();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(response.getBody()).containsExactly("Electronics", "Clothing");
    }
}
