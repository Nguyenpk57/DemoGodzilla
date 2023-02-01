package com.ntt.godzilla.service.impl;

import com.ntt.godzilla.dto.request.CategoryRequestDTO;
import com.ntt.godzilla.entity.Category;
import com.ntt.godzilla.exception.ValidationException;
import com.ntt.godzilla.factory.ResponseStatusEnum;
import com.ntt.godzilla.repository.CategoryRepository;
import com.ntt.godzilla.service.ICategoryService;
import com.ntt.godzilla.util.CommonUtils;
import com.ntt.godzilla.util.Constant;
import com.ntt.godzilla.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> getCategoryByName(CategoryRequestDTO requestDTO, Pageable pageable) {
        if (!StringUtils.hasText(requestDTO.getCategoryName())) {
            return categoryRepository.findCategoryByStatus(Constant.NEW_FLAG, pageable);
        }
        return categoryRepository.findCategoryByStatusAndCategoryNameContaining(Constant.NEW_FLAG, requestDTO.getCategoryName(), pageable);
    }

    @Transactional
    @Override
    public Category createCategory(CategoryRequestDTO requestDTO) {
        if (!ObjectUtils.isEmpty(requestDTO)) {
            //Create Directory With Slug
            String slugName = CommonUtils.toSlug(requestDTO.getCategoryName());
            FileUtils.createFolder(slugName);
            //Create Category
            Category category = new Category();
            category.setCategoryName(requestDTO.getCategoryName());
            category.setDescription(requestDTO.getDescription());
            category.setSlug(slugName);
            category.setStatus(Constant.NEW_FLAG);
            categoryRepository.save(category);
            categoryRepository.flush();
            return category;
        }
        throw new ValidationException(ResponseStatusEnum.BODY_MISSING);
    }

    /**
     * @param id
     * @return boolean
     */
    @Transactional
    @Override
    public Boolean deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new ValidationException(ResponseStatusEnum.ID_NOT_NULL);
        }
        categoryRepository.updateCategoryStatus(id, Constant.DELETE_FLAG);
        return true;
    }
}
