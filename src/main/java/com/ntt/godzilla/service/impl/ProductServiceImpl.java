package com.ntt.godzilla.service.impl;

import com.ntt.godzilla.dto.ListResponseDTO;
import com.ntt.godzilla.dto.PageDTO;
import com.ntt.godzilla.dto.request.ProductRequestDTO;
import com.ntt.godzilla.dto.response.ProductResponseDTO;
import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.repository.ProductRepository;
import com.ntt.godzilla.service.ProductService;
import com.ntt.godzilla.util.ObjectMapperUtils;
import com.ntt.godzilla.util.PageUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductRepository productRepository;


    @Override
    public ListResponseDTO<ProductResponseDTO> getAllProducts(String fieldToSort, String direction, int page, int size) {
        List<Product> productList = productRepository.findAllProducts();
        long totalRecords = productRepository.countAllProducts();

        PageDTO pageDTO = PageUtils.calculatePage(size,page,totalRecords);
        List<ProductResponseDTO> listDto = ObjectMapperUtils.mapAll(productList,ProductResponseDTO.class);
        return new ListResponseDTO<>(listDto,pageDTO);
    }
    @Override
    public void createProduct(ProductRequestDTO requestDTO) {
        Product product = Product.builder()
                .productName(requestDTO.getProductName())
                .productCode(requestDTO.getProductCode())
                .description(requestDTO.getDescription())
                .price(requestDTO.getPrice())
                .discount(requestDTO.getDiscount()).build();
        productRepository.insertProduct(product);
    }
}
