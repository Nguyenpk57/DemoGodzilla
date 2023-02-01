package com.ntt.godzilla.service.impl;

import com.ntt.godzilla.service.IImageService;
import com.ntt.godzilla.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Service
public class ImageServiceImpl implements IImageService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public void save(MultipartFile file, String dir) {
        try {
            File filePath = FileUtils.generateFileDirName(dir);
            Files.copy(file.getInputStream(), filePath.toPath().resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (Exception e) {
            throw new RuntimeException("Could not save the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource loadFile(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
