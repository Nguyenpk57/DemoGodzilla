package com.ntt.godzilla.repository;

import com.ntt.godzilla.entity.ProductCategory;
import com.ntt.godzilla.entity.ProductCategoryId;
import com.slyak.spring.jpa.GenericJpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends GenericJpaRepository<ProductCategory, ProductCategoryId> {
    List<ProductCategory> findAllByCategoryIdAndStatus(Long categoryId, int status);

    ProductCategory findProductCategoriesByProductIdAndCategoryId(Long categoryId, Long productId);

    List<ProductCategory> findAllByProductIdAndStatus(Long productId, int status);

}
