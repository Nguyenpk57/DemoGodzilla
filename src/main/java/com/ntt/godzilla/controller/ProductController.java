package com.ntt.godzilla.controller;

import com.ntt.godzilla.dto.ListResponseDTO;
import com.ntt.godzilla.dto.request.ProductRequestDTO;
import com.ntt.godzilla.dto.response.ProductResponseDTO;
import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ListResponseDTO<ProductResponseDTO>> getAllProduct( @RequestParam(required = false, defaultValue = "1") int page,
                                                                              @RequestParam(required = false, defaultValue = "10") int size,
                                                                              @RequestParam(required = false, defaultValue =  "product_name") String fieldToSort,
                                                                              @RequestParam(required = false, defaultValue = "-1") String direction){
        ListResponseDTO<ProductResponseDTO> products = productService.getAllProducts(fieldToSort,direction,page,size);
        return ResponseEntity.ok(products);
    }
    @PostMapping
    public void createProduct(@RequestBody ProductRequestDTO dto){
        productService.createProduct(dto);
    }

}
