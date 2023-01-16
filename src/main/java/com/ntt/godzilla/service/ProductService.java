package com.ntt.godzilla.service;

import com.ntt.godzilla.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    void createProduct(Product product);
}
