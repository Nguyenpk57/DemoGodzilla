package com.ntt.godzilla.util;

import java.io.File;

public class FileUtils {

    public static void createFolder(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            return;
        }
        folder.mkdirs();
    }
}
