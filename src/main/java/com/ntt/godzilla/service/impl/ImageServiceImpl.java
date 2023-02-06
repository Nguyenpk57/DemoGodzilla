package com.ntt.godzilla.service.impl;

import com.ntt.godzilla.controller.ImageController;
import com.ntt.godzilla.dto.response.FileResponseDTO;
import com.ntt.godzilla.entity.Image;
import com.ntt.godzilla.exception.ValidationException;
import com.ntt.godzilla.factory.ResponseStatusEnum;
import com.ntt.godzilla.repository.ImageRepository;
import com.ntt.godzilla.service.ImageService;
import com.ntt.godzilla.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ImageServiceImpl implements ImageService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Value("${spring.folder-upload-files:}")
    private Path rootLocation;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;

    }

    @Override
    public Boolean uploadImage(MultipartFile file, String categoryName) {
        try {
//            String slugName = CommonUtils.toSlug();
            InputStream inputStream = file.getInputStream();
            File filePath = generateFileDirName(categoryName);
            Files.copy(inputStream, filePath.toPath());
            inputStream.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Could not uploadImage the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource loadFile(String filename, String slugName) {
        try {
            Path file = generateFileDirName(slugName).toPath().resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public Image saveImage(MultipartFile imageFile, String uri, Long productId) {
        Image image = new Image();
        image.setImagePath(uri);
        image.setImageType(imageFile.getContentType());
        image.setImageName(imageFile.getOriginalFilename());
        image.setImageSize(imageFile.getSize());
        image.setProductId(productId);
        imageRepository.save(image);
        return image;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public FileResponseDTO uploadAndSaveImage(String categoryName, MultipartFile imageFile, Long productId) {
        String sourceFolder = CommonUtils.toSlug(categoryName) + "/" + imageFile.getOriginalFilename();
        String uri = MvcUriComponentsBuilder.fromMethodName(ImageController.class, "getFile",imageFile.getOriginalFilename(),CommonUtils.toSlug(categoryName)).build().toString();
        Boolean isUploadSuccess = uploadImage(imageFile, sourceFolder);
        if (isUploadSuccess) {
            Image image = saveImage(imageFile, sourceFolder, productId);
            return FileResponseDTO.builder()
                    .fileName(image.getImageName())
                    .size(image.getImageSize())
                    .fileDownloadUri(uri)
                    .fileType(image.getImageType())
                    .productId(productId)
                    .build();
        }
        throw new ValidationException(ResponseStatusEnum.GENERAL_ERROR);
    }

    public File generateFileDirName(String slugName) {
        return new File(rootLocation + "/" + slugName);
    }

}
