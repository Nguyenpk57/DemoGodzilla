package com.ntt.godzilla.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.nio.file.Path;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    public static void createFolder(String dirName) {
        File fileDirname = new File("./upload" + "/" + dirName);
        if (fileDirname.exists()) {
            logger.info("Folder " + fileDirname + " is already existed!");
            return;
        }
        fileDirname.mkdirs();
    }
}
