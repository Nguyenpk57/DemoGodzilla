package com.ntt.godzilla.service.impl;

import com.ntt.godzilla.dto.request.CategoryRequestDTO;
import com.ntt.godzilla.entity.Category;
import com.ntt.godzilla.exception.BadRequestValidationException;
import com.ntt.godzilla.exception.ValidationException;
import com.ntt.godzilla.factory.ResponseStatusEnum;
import com.ntt.godzilla.repository.CategoryRepository;
import com.ntt.godzilla.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> getCategoryByName(CategoryRequestDTO requestDTO, Pageable pageable) {
        if (StringUtils.hasText(requestDTO.getCategoryName())){
            return categoryRepository.findAll(pageable);
        }
         return categoryRepository.findCategoryByCategoryName(requestDTO.getCategoryName(), pageable);
    }

    @Override
    public Category createCategory(CategoryRequestDTO requestDTO) {
        if (!ObjectUtils.isEmpty(requestDTO)){
            Category category = new Category();
//            BeanUtils.copyProperties(requestDTO, category);
            category.setCategoryId(requestDTO.getCategoryId());
            category.setCategoryName(requestDTO.getCategoryName());
            category.setDescription(requestDTO.getDescription());
            category.setCreate_time(requestDTO.getCreateTime());
            category.setCreate_user(requestDTO.getCreateUser());
            category.setUpdateUser(requestDTO.getUpdateUser());
            category.setUpdateTime(requestDTO.getUpdateTime());
           return categoryRepository.save(category);
        }
        throw new ValidationException(ResponseStatusEnum.BODY_MISSING);
    }
}
