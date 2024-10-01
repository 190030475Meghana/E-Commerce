package com.publicissapient.ecommerce.service;


import com.publicissapient.ecommerce.entities.Category;
import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    List<Category> getAllCategories();
}

