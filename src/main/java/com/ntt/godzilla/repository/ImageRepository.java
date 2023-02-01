package com.ntt.godzilla.repository;

import com.ntt.godzilla.entity.Product;
import com.slyak.spring.jpa.GenericJpaRepository;

public interface ImageRepository  extends GenericJpaRepository<Product, Long> {

}
