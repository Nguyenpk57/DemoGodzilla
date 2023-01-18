package com.ntt.godzilla.controller;

import com.ntt.godzilla.dto.request.ProductRequestDTO;
import com.ntt.godzilla.dto.response.RecordListResponse;
import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.factory.ResponseFactory;
import com.ntt.godzilla.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get(@RequestBody ProductRequestDTO requestDTO) {
        try {
            Page<Product> products = productService.getProductsByName(requestDTO, buildPageRequest(requestDTO));
            return responseFactory.success(RecordListResponse.builder()
                            .currentPage(products.getNumber())
                            .pageSize(products.getSize())
                            .total(products.getTotalElements())
                            .records(products.stream().collect(Collectors.toList())).build()
                    , RecordListResponse.class
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
