package com.ojas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TaskManager {

    public List<Task> tasks;
    public final String PATH = "./src/main/java/com/ojas/tasks.json";

    public TaskManager() {
        tasks = new java.util.ArrayList<>();
    }

    public void getTasksByStatus(String status) {
        List<Task> filteredTasks = this.tasks.stream()
                .filter(task -> task.getStatus().equalsIgnoreCase(status))
                .toList();
        Utils.printMulitpleTasks(filteredTasks);
    }

    public List<Task> loadTasks() {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            StringBuilder rawTask = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (line.contains("[") || line.contains("]")) {
                    continue;
                }

                if (isLineStartsWith(line, '{')) {
                    rawTask.append(line);
                } else if (isLineEndsWith(line, '}')) {
                    rawTask.append(line);
                    Task task = convertToTaskObj(rawTask.toString());
                    tasks.add(task);
                    rawTask = new StringBuilder();
                } else {
                    rawTask.append(line);
                }

            }
        } catch (Exception e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) throws IOException {

        StringBuilder sb = new StringBuilder();

        sb.append("[\n");
        for (Task task : tasks) {
            sb.append(task.toString());
            if (tasks.indexOf(task) != tasks.size() - 1){
                sb.append(",\n");
            }
        }
        sb.append("\n]");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH))){
            writer.write(sb.toString());
        } catch (IOException e) {
            System.out.println("Error writing tasks: " + e.getMessage());
        }

    }

    public Task convertToTaskObj(String rawTask) {

        String[] rawArray = rawTask.split(",");

        Task task = new Task();

        for (String line : rawArray) {
            String[] keyValue = line.split(":", 2);

            if (keyValue.length == 2) {
                String key = extractQuotedText(keyValue[0]);
                String value = extractQuotedText(keyValue[1]);
                
                
                switch (removeSpacesFromKey(key)) {
                    case "id" -> task.setId(Integer.parseInt(value));
                    case "description" -> task.setDescription(value);
                    case "status" -> task.setStatus(value);
                    case "createdAt" -> task.setCreatedAt(convertToDate(value));
                    case "updatedAt" -> task.setUpdatedAt(convertToDate(value));
                    default -> {
                        System.out.println("Unknown key: " + key);
                        throw new AssertionError();
                    }
                }
            }
        }
        return task;

    }

    public boolean isLineEndsWith(String line, char character) {
        if (line == null || line.isEmpty()) {
            return false;
        }

        // Start from the end of the string
        for (int i = line.length() - 1; i >= 0; i--) {
            char ch = line.charAt(i);
            // Skip spaces and commas
            if (ch == ' ' || ch == ',') {
                continue;
            }
            // Compare first non-space, non-comma character from the end
            return ch == character;
        }

        // If line only contains spaces or commas
        return false;
    }

    public boolean isLineStartsWith(String line, char character) {
        if (line == null || line.isEmpty()) {
            return false;
        }
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ' ') {
                return line.charAt(i) == character;
            }
        }

        return false;
    }

    public String removeSpacesFromKey(String key) {
        return key.replace(" ", "");
    }

    public static Date convertToDate(String dateString) {
        // Pattern matching "Fri Oct 17 23:46:23 IST 2025"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        // Parse as ZonedDateTime (because of IST timezone)
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter);

        // Convert to java.util.Date
        return Date.from(zonedDateTime.toInstant());
    }


    public static String extractQuotedText(String line) {
        int firstQuote = line.indexOf('"');
        int secondQuote = line.indexOf('"', firstQuote + 1);

        if (firstQuote != -1 && secondQuote != -1 && secondQuote > firstQuote) {
            return line.substring(firstQuote + 1, secondQuote);
        }

        return null; // no valid quotes found
    }

}
