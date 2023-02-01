package com.ntt.godzilla.controller;

import com.ntt.godzilla.factory.ResponseFactory;
import com.ntt.godzilla.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/images")
public class ImageControler extends BaseController {

    @Autowired
    private HttpServletRequest request;

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private IImageService imageService;

    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    public ResponseEntity<?> uploadImages(@RequestBody MultipartFile files) {
        //TODO

        return responseFactory.success();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list-product-id/{productId}")
    public ResponseEntity<?> getImagesByProductId(@PathVariable Long productId) {
        //TODO

        return responseFactory.success();
    }
}
