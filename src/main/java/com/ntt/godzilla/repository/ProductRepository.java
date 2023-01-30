package com.ntt.godzilla.repository;

import com.ntt.godzilla.entity.Product;
import com.slyak.spring.jpa.GenericJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository extends GenericJpaRepository<Product, Long> {
    Page<Product> findAllByStatus(Integer status, Pageable pageable);
    Page<Product> getByProductNameContainingAndStatus(String name, Integer status, Pageable pageable);

    List<Product> getProductsByProductIdIn(List productIds);

}
