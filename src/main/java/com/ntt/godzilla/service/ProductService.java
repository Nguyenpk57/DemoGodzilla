package com.ntt.godzilla.service;

import com.ntt.godzilla.dto.ListResponseDTO;
import com.ntt.godzilla.dto.request.ProductRequestDTO;
import com.ntt.godzilla.dto.response.ProductResponseDTO;

public interface ProductService {
    ListResponseDTO<ProductResponseDTO> getAllProducts(String fieldToSort, String direction, int page, int size);
    void createProduct(ProductRequestDTO requestDTO);
}
