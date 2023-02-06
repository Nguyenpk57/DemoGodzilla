package com.ntt.godzilla.service;

import com.ntt.godzilla.dto.request.CategoryRequestDTO;
import com.ntt.godzilla.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<Category> getCategories(CategoryRequestDTO requestDTO, Pageable pageable);

    Category createCategory(CategoryRequestDTO requestDTO);

    Boolean deleteCategory(Long id);
}
