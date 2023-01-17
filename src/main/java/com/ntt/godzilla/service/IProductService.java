package com.ntt.godzilla.service;

import com.ntt.godzilla.dto.request.ProductRequestDTO;
import com.ntt.godzilla.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<Product> getProductsByName(ProductRequestDTO requestDTO, Pageable pageable);
}
