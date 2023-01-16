package com.ntt.godzilla.controller;

import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @PostMapping
    public void createProduct(@RequestBody Product dto){
        productService.createProduct(dto);
    }

}
