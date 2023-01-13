package com.ntt.godzilla.controller;

import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.factory.ResponseFactory;
import com.ntt.godzilla.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/product")
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

    @GetMapping("/get")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return responseFactory.success(products);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
