package com.ntt.godzilla.repository;

import com.ntt.godzilla.dto.request.CategoryRequestDTO;
import com.ntt.godzilla.entity.Category;
import com.slyak.spring.jpa.GenericJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends GenericJpaRepository<Category,Long> {
    Page<Category> findCategoryByCategoryName(String categoryName,  Pageable pageable);
}
