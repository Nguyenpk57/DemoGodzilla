package com.ntt.godzilla.service.impl;

import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.repository.ProductRepository;
import com.ntt.godzilla.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        productRepository.findAll().forEach(products::add);
        return products;
    }
}
