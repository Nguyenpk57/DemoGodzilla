package com.ntt.godzilla.service;

import com.ntt.godzilla.dto.request.ProductRequestDTO;
import com.ntt.godzilla.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> getProductsByName(ProductRequestDTO requestDTO, Pageable pageable);

    Product getProductsById(Long productId);

    List<Product> getProductsByCategoryId(Long categoryId);

    Product addProduct(ProductRequestDTO requestDTO);

    Product updateProduct(ProductRequestDTO requestDTO);

    void deleteProduct(Long productId);
}
