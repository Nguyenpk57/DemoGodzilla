package com.ntt.godzilla.service.impl;

import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.repository.ProductRepository;
import com.ntt.godzilla.service.ProductService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAllProducts();
    }

    @Override
    public void createProduct(Product product) {
        productRepository.insertProduct(product);
    }
}
