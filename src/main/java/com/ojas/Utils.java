package com.ojas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    public static void printMulitpleTasks(List<Task> tasks) {
        List<String[]> rows = new ArrayList<>();
        for (Task t : tasks) {
            rows.add(new String[]{String.valueOf(t.getId()), t.getDescription(), t.getStatus()});
        }

        TablePrinter.print(new String[]{"ID", "Description", "Status"}, rows);
    }

    public static void printSingleTask(Task task) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{String.valueOf(task.getId()), task.getDescription(), task.getStatus()});
        TablePrinter.print(new String[]{"ID", "Description", "Status"}, rows);
    }
}
