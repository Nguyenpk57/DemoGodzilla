package com.ntt.godzilla.service.impl;

import com.ntt.godzilla.dto.request.ProductRequestDTO;
import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.repository.ProductRepository;
import com.ntt.godzilla.service.IProductService;
import com.ntt.godzilla.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getProductsByName(ProductRequestDTO requestDTO, Pageable pageable) {
        if(org.apache.commons.lang3.StringUtils.isEmpty(requestDTO.getProductName())) {
            return productRepository.findAll(pageable);
        }
        return productRepository.getByProductNameContaining(requestDTO.getProductName(), pageable);
    }

}
