package com.ecommerce.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.project.dto.CategoryRequest;
import com.ecommerce.project.dto.CategoryResponse;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/public/categories")
    public ResponseEntity<Page<CategoryResponse>> getALlCategories(
            @RequestParam(defaultValue =  "0") int page,
            @RequestParam(defaultValue =  "10") int size,
            @RequestParam(defaultValue = "categoryName") String sortBy

    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<CategoryResponse> categoryResponses = categoryService.getAllCategories(pageable);

        return new ResponseEntity<>(categoryResponses, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory (@RequestBody CategoryRequest category){
        categoryService.CreateCategory(category);
        // return ResponseEntity.ok(status)
        return new ResponseEntity<>( "category added", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/public/categories/{categoryId}")
    public  ResponseEntity<String> updateCategory (@RequestBody Category category,
                                                   @PathVariable Long categoryId){
        try{
            Category savedCategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>( HttpStatus.OK);
        }catch(ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/public/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory (@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
