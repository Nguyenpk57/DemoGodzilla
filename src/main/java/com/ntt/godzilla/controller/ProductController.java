package com.ntt.godzilla.controller;

import com.ntt.godzilla.dto.request.ProductRequestDTO;
import com.ntt.godzilla.dto.response.RecordListResponse;
import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.factory.ResponseFactory;
import com.ntt.godzilla.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {
    private final ResponseFactory responseFactory;

    private final ProductService productService;

    public ProductController(ResponseFactory responseFactory, ProductService productService) {
        this.responseFactory = responseFactory;
        this.productService = productService;
    }

    @GetMapping
    @RolesAllowed("admin")
    public ResponseEntity<?> getProductsByNameOrCategoryId(@RequestParam(required = false, defaultValue = "") String productName,
                                                           @RequestParam(required = false, defaultValue = "0") Integer page,
                                                           @RequestParam(required = false, defaultValue = "10") Integer offset) {

        ProductRequestDTO requestDTO = ProductRequestDTO.builder().productName(productName).page(page).offset(offset).build();
        Page<Product> products = productService.getProductsByName(requestDTO, buildPageRequest(requestDTO));
        return responseFactory.success(RecordListResponse.builder().currentPage(products.getNumber()).pageSize(products.getSize()).total(products.getTotalElements()).records(products.stream().collect(Collectors.toList())).build(), RecordListResponse.class);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductsById(@PathVariable Long productId) {
        return responseFactory.success(productService.getProductsById(productId));
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequestDTO requestDTO) {
        Product product = productService.addProduct(requestDTO);
        return responseFactory.success(product);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequestDTO requestDTO) {
        Product product = productService.updateProduct(requestDTO);
        return responseFactory.success(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return responseFactory.success();
    }
}
