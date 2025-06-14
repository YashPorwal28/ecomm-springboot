package com.ecommerce.project.service;

import com.ecommerce.project.dto.CategoryRequest;
import com.ecommerce.project.dto.CategoryResponse;
import com.ecommerce.project.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryResponse> getAllCategories (Pageable pageable);
    void CreateCategory (CategoryRequest category);
    Category updateCategory(Category category, Long categoryId);
    void deleteCategory (Long categoryId);
}
