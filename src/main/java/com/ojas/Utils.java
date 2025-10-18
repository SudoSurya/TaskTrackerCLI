package com.ojas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

    public static boolean isFileExists(String path) {
        Path filePath = Paths.get(path);
        return !Files.notExists(filePath);
    }

    public static void createFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        Files.createFile(filePath);
        String defaultContent = "[\n\n]";
        Files.writeString(filePath, defaultContent);
        System.out.println("File not found. Creating new file at " + filePath);
    }

}
