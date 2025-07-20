package com.ecommerce.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ecommerce.project.dto.product.ProductRequest;
import com.ecommerce.project.dto.product.ProductResponse;


public interface ProductsService {
    ProductResponse createProduct(ProductRequest productRequest);

    Page<ProductResponse> getAllProducts(Pageable pageable);
}
