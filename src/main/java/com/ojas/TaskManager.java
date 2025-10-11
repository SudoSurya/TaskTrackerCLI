package com.ojas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TaskManager {

    public List<Task> tasks;
    public final String PATH = "./src/main/java/com/ojas/tasks.json";

    public TaskManager() {
    }

    public Task convertToTask(String obj) {
        return new Task();
    }

    public void loadTasks() {
        if (!Utils.isFileExists(PATH)) {
            try {
                Utils.createFile(PATH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (line.contains("[") || line.contains("]")) {
                    continue;
                }
                if (line.contains("{")) {
                    sb.append(line);
                }
                if (line.contains("}")) {
                    sb.append(line);
                }
                sb.append(line);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
