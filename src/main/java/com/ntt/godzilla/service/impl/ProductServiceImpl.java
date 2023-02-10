package com.ntt.godzilla.service.impl;

import com.ntt.godzilla.dto.request.ProductRequestDTO;
import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.entity.ProductCategory;
import com.ntt.godzilla.exception.ValidationException;
import com.ntt.godzilla.factory.ResponseStatusEnum;
import com.ntt.godzilla.repository.ProductCategoryRepository;
import com.ntt.godzilla.repository.ProductRepository;
import com.ntt.godzilla.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public Page<Product> getProductsByName(ProductRequestDTO requestDTO, Pageable pageable) {
        if (StringUtils.isEmpty(requestDTO.getProductName())) {
            return productRepository.findAllByStatus(Product.NEW, pageable);
        }
        return productRepository.getByProductNameContainingAndStatus(requestDTO.getProductName(), Product.NEW, pageable);
    }

    @Override
    public Product getProductsById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new ValidationException(ResponseStatusEnum.ERROR_NOT_FOUND);
        }
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        List<ProductCategory> productCategories = productCategoryRepository.findAllByCategoryIdAndStatus(categoryId, ProductCategory.NEW);
        List<Long> productIds = productCategories.stream()
                .map(ProductCategory::getProductId)
                .collect(Collectors.toList());
        return productRepository.getProductsByProductIdIn(productIds);
    }

    @Override
    @Transactional
    public Product addProduct(ProductRequestDTO requestDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(requestDTO, product);
        product = productRepository.save(product);
        productRepository.flush();
        Long productId = product.getProductId();

        if (!Objects.isNull(requestDTO.getCategoryIds())) {
            addProductCategory(requestDTO.getCategoryIds(), productId);
        }
        return product;
    }

    @Override
    @Transactional
    public Product updateProduct(ProductRequestDTO requestDTO) {
        if (Objects.isNull(requestDTO.getProductId())) {
            throw new ValidationException(ResponseStatusEnum.FIELD_MISSING);
        }
        Product product = getProductsById(requestDTO.getProductId());

        BeanUtils.copyProperties(requestDTO, product);
        product = productRepository.save(product);

        if (!Objects.isNull(requestDTO.getCategoryIds())) {
            //delete all old list product category by product id
            List<ProductCategory> productCategoriesDelete = productCategoryRepository.findAllByProductIdAndStatus(requestDTO.getProductId(), ProductCategory.NEW);
            productCategoriesDelete.forEach(productCategory ->
                productCategory.setStatus(ProductCategory.DELETE)
            );
            productCategoryRepository.saveAll(productCategoriesDelete);

            //insert new list product category when update
            addProductCategory(requestDTO.getCategoryIds(), requestDTO.getProductId());
        }
        return product;
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        Product product = getProductsById(productId);
        product.setStatus(Product.DELETE);
        productRepository.save(product);

        List<ProductCategory> productCategories = productCategoryRepository.findAllByProductIdAndStatus(productId, ProductCategory.NEW);
        productCategories.forEach(productCategory ->
            productCategory.setStatus(ProductCategory.DELETE)
        );
        productCategoryRepository.saveAll(productCategories);
    }

    private void addProductCategory(List<Long> categoryIds, Long productId) {
        List<ProductCategory> productCategoriesNew = new ArrayList<>();
        categoryIds.forEach(id -> {
            ProductCategory productCategory = ProductCategory.builder()
                    .productId(productId)
                    .categoryId(id)
                    .build();
            productCategoriesNew.add(productCategory);
        });
        productCategoryRepository.saveAll(productCategoriesNew);
    }

}
