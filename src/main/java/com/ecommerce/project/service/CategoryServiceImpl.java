package com.ecommerce.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.project.dto.CategoryRequest;
import com.ecommerce.project.dto.CategoryResponse;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Page<CategoryResponse> getAllCategories(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        return categoryPage.map(category -> new CategoryResponse(category.getCategoryName()));
    }

    @Override
    public void CreateCategory(CategoryRequest category) {
        Category category1 = new Category();
        category1.setCategoryName(category.getCategoryName());

        categoryRepository.save(category1);

    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);

    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Category category1 = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("category not found"));
        category1.setCategoryName(category.getCategoryName());
        return categoryRepository.save(category1);

    }
}
