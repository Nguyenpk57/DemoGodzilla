package com.ntt.godzilla.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    public void save(MultipartFile file, String dir);

    public Resource loadFile(String filename);

    public void deleteAll();

}
