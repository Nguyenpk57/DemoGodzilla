package com.ntt.godzilla.repository;

import com.ntt.godzilla.entity.Product;

import java.util.List;

public interface ProductRepository {
    void insertProduct(Product product);
    List<Product> findAllProducts();
}
