package com.ntt.godzilla.service.impl;

import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getProductsByName_Success() {


    }

//    @Test
//    void getProductsByName_NoResult() {
//
//        Pageable pageable = PageRequest.of(1, 2);
//
//        Page<Product> productResult = productRepository.getByProductNameContaining("laptop", pageable);
//
//        assertThat(productResult.getSize()).isEqualTo(0);
//
////        verify(productRepository).getByProductNameContaining("laptop", pageable);
//
//    }
}