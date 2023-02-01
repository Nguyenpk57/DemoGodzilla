package com.ntt.godzilla.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.nio.file.Path;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    @Value("${spring.folder-upload-files:}")
    private static Path rootLocation;

    public static File generateFileDirName(String dirName){
        return new File(rootLocation + "/" + dirName);
    }

    public static void createFolder(String dirName) {
        File fileDirname = generateFileDirName(dirName);
        if (fileDirname.exists()) {
            logger.info("Folder " + fileDirname + " is already existed!");
            return;
        }
        fileDirname.mkdirs();
    }
}
