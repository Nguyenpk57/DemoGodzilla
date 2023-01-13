package com.ntt.godzilla.repository;

import com.ntt.godzilla.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
