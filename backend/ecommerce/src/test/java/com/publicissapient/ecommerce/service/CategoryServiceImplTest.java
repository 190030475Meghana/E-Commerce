package com.publicissapient.ecommerce.service;


import com.publicissapient.ecommerce.entities.Category;
import com.publicissapient.ecommerce.repository.CategoryRepository;
import com.publicissapient.ecommerce.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCategory() {
        Category categoryToAdd = new Category();
        categoryToAdd.setName("Test Category");

        when(categoryRepository.save(any(Category.class))).thenReturn(categoryToAdd);

        Category addedCategory = categoryService.addCategory(categoryToAdd);
        verify(categoryRepository, times(1)).save(any(Category.class));
        assertEquals("Test Category", addedCategory.getName());
    }

    @Test
    void testGetAllCategories() {
        Category category1 = new Category();
        category1.setCategoryId(1L);
        category1.setName("Category 1");

        Category category2 = new Category();
        category2.setCategoryId(2L);
        category2.setName("Category 2");

        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(category1);
        mockCategories.add(category2);

        when(categoryRepository.findAll()).thenReturn(mockCategories);

        List<Category> categoryList = categoryService.getAllCategories();
        verify(categoryRepository, times(1)).findAll();

        assertEquals(2, categoryList.size());

        Category retrievedCategory1 = categoryList.get(0);
        assertEquals(1L, retrievedCategory1.getCategoryId());
        assertEquals("Category 1", retrievedCategory1.getName());

        Category retrievedCategory2 = categoryList.get(1);
        assertEquals(2L, retrievedCategory2.getCategoryId());
        assertEquals("Category 2", retrievedCategory2.getName());
    }
}

