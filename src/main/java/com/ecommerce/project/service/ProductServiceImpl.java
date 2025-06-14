package com.ecommerce.project.service;

import com.ecommerce.project.dto.product.ProductRequest;
import com.ecommerce.project.dto.product.ProductResponse;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.repository.CategoryRepository;
import com.ecommerce.project.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductsService {

  @Autowired ProductsRepository productsRepository;

  @Autowired CategoryRepository categoryRepository;

  @Override
  public ProductResponse createProduct(ProductRequest productRequest) {

    Category category =
        categoryRepository
            .findByCategoryName(productRequest.getCategory())
            .orElseGet(
                () -> {
                  Category category1 = new Category();
                  category1.setCategoryName(productRequest.getCategory());

                  return category1;
                });

    Product product = new Product();
    product.setProductTitle(productRequest.getProductTitle());
    product.setPrice(productRequest.getPrice());

    product.setCategory(category);

    Product savedProduct = productsRepository.save(product);
    return new ProductResponse(
        savedProduct.getProductTitle(),
        savedProduct.getPrice(),
        savedProduct.getCategory().getCategoryName());
  }
}
