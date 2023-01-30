package com.ntt.godzilla.repository;

import com.ntt.godzilla.entity.ProductCategory;
import com.slyak.spring.jpa.GenericJpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends GenericJpaRepository<ProductCategory, ProductCategoryId> {
    List<ProductCategory> findAllByCategoryId(Long categoryId);

    ProductCategory findProductCategoriesByProductIdAndCategoryId(Long categoryId, Long productId);

    List<ProductCategory> findAllByProductId(Long productId);

}
