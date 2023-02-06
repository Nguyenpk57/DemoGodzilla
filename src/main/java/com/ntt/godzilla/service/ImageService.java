package com.ntt.godzilla.service;

import com.ntt.godzilla.dto.response.FileResponseDTO;
import com.ntt.godzilla.entity.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Boolean uploadImage(MultipartFile file, String categoryName);

    Resource loadFile(String filename, String slugName);

    Image saveImage(MultipartFile imageFile, String uri, Long productId);
    void deleteAll();
    FileResponseDTO uploadAndSaveImage(String categoryName,MultipartFile imageFile,Long productId);

}
