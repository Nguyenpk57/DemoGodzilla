package com.ntt.godzilla.controller;

import com.ntt.godzilla.dto.request.ProductRequestDTO;
import com.ntt.godzilla.dto.response.RecordListResponse;
import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.factory.ResponseFactory;
import com.ntt.godzilla.service.IProductService;
import javax.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private HttpServletRequest request;

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private IProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    @RolesAllowed("admin")
    public ResponseEntity<?> getProductsByNameOrCategoryId(@RequestParam(required = false, defaultValue = "") String productName,
                                                           @RequestParam(required = false, defaultValue = "0") Integer page,
                                                           @RequestParam(required = false, defaultValue = "10") Integer offset) {

        ProductRequestDTO requestDTO = ProductRequestDTO.builder().productName(productName).page(page).offset(offset).build();
        Page<Product> products = productService.getProductsByName(requestDTO, buildPageRequest(requestDTO));
        return responseFactory.success(RecordListResponse.builder().currentPage(products.getNumber()).pageSize(products.getSize()).total(products.getTotalElements()).records(products.stream().collect(Collectors.toList())).build(), RecordListResponse.class);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{productId}")
    public ResponseEntity<?> getProductsById(@PathVariable Long productId) {
        return responseFactory.success(productService.getProductsById(productId));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/category-id")
    public ResponseEntity<?> getProductsByCategoryId(@RequestParam Long categoryId) {
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        return responseFactory.success(products);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody ProductRequestDTO requestDTO) {
        Product product = productService.addProduct(requestDTO);
        return responseFactory.success(product);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequestDTO requestDTO) {
        Product product = productService.updateProduct(requestDTO);
        return responseFactory.success(product);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return responseFactory.success();
    }
}
