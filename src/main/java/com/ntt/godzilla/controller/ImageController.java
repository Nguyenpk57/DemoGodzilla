package com.ntt.godzilla.controller;

import com.ntt.godzilla.dto.response.FileResponseDTO;
import com.ntt.godzilla.factory.ResponseFactory;
import com.ntt.godzilla.factory.ResponseStatusEnum;
import com.ntt.godzilla.service.IImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/images")
public class ImageController extends BaseController {
    private final ResponseFactory responseFactory;
    private final IImageService imageService;
    public ImageController(ResponseFactory responseFactory, IImageService imageService) {
        this.responseFactory = responseFactory;
        this.imageService = imageService;
    }
    @Override
    public HttpServletRequest getRequest() {
        return null;
    }
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImages(@RequestParam MultipartFile file, @RequestParam String categoryName, @RequestParam Long productId) {
        String message;
        try {
            FileResponseDTO responseDTO = imageService.uploadAndSaveImage(categoryName,file,productId);
            return responseFactory.success(responseDTO);
        }catch (Exception e) {
            message = "File's already existed : " + file.getOriginalFilename() + "!";
            return responseFactory.success(HttpStatus.CONFLICT, ResponseStatusEnum.DATA_IN_USE_CANNOT_BE_DELETE_OR_UPDATE.getCode(),message);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{productId}")
    public ResponseEntity<?> getImagesByProductId(@PathVariable Long productId) {
        //TODO

        return responseFactory.success();
    }
    @GetMapping("/download/{categoryName}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable("filename") String filename, @PathVariable("categoryName") String categoryName) {
        Resource file = imageService.loadFile(filename, categoryName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
