package com.ojas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    public static boolean isFileExists(String path) {
        Path filePath = Paths.get(path);
        if (Files.notExists(filePath)) {
            return false;
        }
        return true;
    }

    public static void createFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        Files.createFile(filePath);
        String defaultContent = "{}";
        Files.writeString(filePath, defaultContent);
        System.out.println("File not found. Creating new file at " + filePath);
    }

    public static void readFile(String path) {
        if (!isFileExists(path)) {
            try {
                createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
