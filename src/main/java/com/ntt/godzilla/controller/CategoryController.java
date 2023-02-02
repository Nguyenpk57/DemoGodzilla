package com.ntt.godzilla.controller;

import com.ntt.godzilla.dto.request.CategoryRequestDTO;
import com.ntt.godzilla.dto.response.RecordListResponse;
import com.ntt.godzilla.entity.Category;
import com.ntt.godzilla.factory.ResponseFactory;
import com.ntt.godzilla.service.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final HttpServletRequest request;
    private final ResponseFactory responseFactory;
    private final ICategoryService categoryService;

    public CategoryController(HttpServletRequest request, ResponseFactory responseFactory, ICategoryService categoryService) {
        this.request = request;
        this.responseFactory = responseFactory;
        this.categoryService = categoryService;
    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllCategories(@RequestParam(required = false) String categoryName,
                                              @RequestParam(required = false) Long id,
                                              @RequestParam Integer page,
                                              @RequestParam Integer size) {
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO();
        categoryRequestDTO.setCategoryName(categoryName);
        categoryRequestDTO.setCategoryId(id);
        Page<Category> categories = categoryService.getCategoryByName(categoryRequestDTO, PageRequest.of(page,size));
        return responseFactory.success(RecordListResponse.builder()
                        .currentPage(categories.getNumber())
                        .pageSize(categories.getSize())
                        .total(categories.getTotalElements())
                        .records(categories.stream().collect(Collectors.toList())).build()
                , RecordListResponse.class);
    }

    @PostMapping("/")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequestDTO requestDTO) {
        return responseFactory.success(categoryService.createCategory(requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return responseFactory.success(categoryService.deleteCategory(id));
    }
}