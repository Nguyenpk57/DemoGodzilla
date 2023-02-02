package com.ntt.godzilla.repository;

import com.ntt.godzilla.entity.Image;
import com.ntt.godzilla.entity.Product;
import com.slyak.spring.jpa.GenericJpaRepository;
import org.springframework.stereotype.Repository;

public interface ImageRepository  extends GenericJpaRepository<Image, Long> {

}
