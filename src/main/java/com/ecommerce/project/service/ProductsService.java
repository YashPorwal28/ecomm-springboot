package com.ecommerce.project.service;

import com.ecommerce.project.dto.product.ProductRequest;
import com.ecommerce.project.dto.product.ProductResponse;


public interface ProductsService {
    ProductResponse createProduct (ProductRequest productRequest);
}
