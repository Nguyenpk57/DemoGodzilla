package com.ntt.godzilla.controller;

import com.ntt.godzilla.factory.ResponseFactory;
import com.ntt.godzilla.factory.ResponseStatusEnum;
import com.ntt.godzilla.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/images")
public class ImageControler extends BaseController {
    private final HttpServletRequest request;
    private final ResponseFactory responseFactory;
    private final IImageService imageService;
    public ImageControler(HttpServletRequest request, ResponseFactory responseFactory, IImageService imageService) {
        this.request = request;
        this.responseFactory = responseFactory;
        this.imageService = imageService;
    }


    @Override
    public HttpServletRequest getRequest() {
        return null;
    }
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    public ResponseEntity<?> uploadImages(@RequestBody MultipartFile file, @RequestParam String dir) {
        String message = "";
        String fileType= file.getContentType();
        try {
            imageService.save(file,dir);
            return responseFactory.success(HttpStatus.OK, ResponseStatusEnum.SUCCESS.getCode(),message);
        }catch (Exception e) {
            message = "File's already existed : " + file.getOriginalFilename() + "!";
            return responseFactory.success(HttpStatus.OK, ResponseStatusEnum.SUCCESS.getCode(),message);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{productId}")
    public ResponseEntity<?> getImagesByProductId(@PathVariable Long productId) {
        //TODO

        return responseFactory.success();
    }


}
