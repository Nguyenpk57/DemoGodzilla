package com.ntt.godzilla.repository;

import com.ntt.godzilla.entity.Product;
import com.slyak.spring.jpa.GenericJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends GenericJpaRepository<Product, Long> {
    Page<Product> getByProductNameContaining(String name, Pageable pageable);

}
