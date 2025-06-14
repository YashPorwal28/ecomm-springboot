package com.ecommerce.project.controller;

import com.ecommerce.project.dto.product.ProductRequest;
import com.ecommerce.project.dto.product.ProductResponse;
import com.ecommerce.project.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello (){
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @PostMapping("/admin/product")
    public ResponseEntity<ProductResponse> createProduct (@RequestBody ProductRequest productRequest){
        ProductResponse productResponse =  productsService.createProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

}
