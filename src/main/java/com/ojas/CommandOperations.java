package com.ojas;

import java.io.IOException;
import java.util.List;

public class CommandOperations {

    public static final String PATH = "./src/main/java/com/ojas/tasks.json";

    public static boolean isTaskDescriptionVaild(String description) {
        return (description == null || description.trim().isEmpty());
    }

    public static void add(String[] args) {
        String description = args[1];

        if (isTaskDescriptionVaild(description)) {
            System.out.println("Invalid task description provided.");
            return;
        }
        if (!Utils.isFileExists(PATH)) {
            try {
                Utils.createFile(PATH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        TaskManager taskManager = new TaskManager();
        List<Task> tasks = taskManager.loadTasks();

        int newTaskId;
        if (tasks.isEmpty()) {
            newTaskId = tasks.size() + 1;
        } else {
            newTaskId = tasks.get(tasks.size() - 1).getId() + 1;
        }

        Task newTask = new Task(newTaskId, description, "todo", new java.util.Date(), new java.util.Date());
        tasks.add(newTask);
        try {
            taskManager.saveTasks(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("New Task Created: ");
        TablePrinter.printSingleTask(newTask);

    }

    public static void update(String[] args) {
        String taskId = args[1];
        String newDescription = args[2];

        if (!Utils.isFileExists(PATH)) {
            System.out.println("Tasks file does not exist. create some tasks first.");
        }

        TaskManager taskManager = new TaskManager();
        List<Task> tasks = taskManager.loadTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks found to update. Create some tasks first.");
            return;
        }
        if (taskId == null || taskId.trim().isEmpty()) {
            System.out.println("Invalid task ID provided for update.");
            return;
        }
        if (tasks.stream().filter(t -> t.getId() == Integer.parseInt(taskId)).toList().isEmpty()) {
            System.out.println("No task found with ID: " + taskId);
            return;
        }

        tasks.stream()
                .filter(task -> task.getId() == Integer.parseInt(taskId))
                .findFirst()
                .ifPresent(task -> {
                    task.setDescription(newDescription);
                    task.setUpdatedAt(new java.util.Date());
                });

        try {
            taskManager.saveTasks(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Task with ID " + taskId + " has been updated.");
        TablePrinter.printSingleTask(tasks.stream()
                .filter(t -> t.getId() == Integer.parseInt(taskId))
                .findFirst()
                .orElse(null));
    }

    public static void delete(String[] args) {
        String taskId = args[1];
        if (!Utils.isFileExists(PATH)) {
            System.out.println("Tasks file does not exist. create some tasks first.");
        }
        TaskManager taskManager = new TaskManager();
        List<Task> tasks = taskManager.loadTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found to delete. Create some tasks first.");
            return;
        }
        if (taskId == null || taskId.trim().isEmpty()) {
            System.out.println("Invalid task ID provided for update.");
            return;
        }
        if (tasks.stream().filter(t -> t.getId() == Integer.parseInt(taskId)).toList().isEmpty()) {
            System.out.println("No task found with ID: " + taskId);
            return;
        }

        List<Task> updatedTasks = tasks.stream()
                .filter(task -> task.getId() != Integer.parseInt(taskId))
                .toList();

        try {
            taskManager.saveTasks(updatedTasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task with ID " + taskId + " has been deleted.");
        System.out.println("Updated Task List:");
        TablePrinter.printMulitpleTasks(updatedTasks);
    }

    public static void markInProgress(String[] args) {
        String taskId = args[1];
        if (!Utils.isFileExists(PATH)) {
            System.out.println("Tasks file does not exist. create some tasks first.");
        }
        TaskManager taskManager = new TaskManager();
        List<Task> tasks = taskManager.loadTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found to mark. Create some tasks first.");
            return;
        }
        if (taskId == null || taskId.trim().isEmpty()) {
            System.out.println("Invalid task ID provided for update.");
            return;
        }
        if (tasks.stream().filter(t -> t.getId() == Integer.parseInt(taskId)).toList().isEmpty()) {
            System.out.println("No task found with ID: " + taskId);
            return;
        }

        tasks.stream()
                .filter(task -> task.getId() == Integer.parseInt(taskId))
                .findFirst()
                .ifPresent(task -> {
                    task.setStatus("in-progress");
                    task.setUpdatedAt(new java.util.Date());
                });

        try {
            taskManager.saveTasks(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Task with ID " + taskId + " has been marked as in-progress.");
        TablePrinter.printSingleTask(tasks.stream()
                .filter(t -> t.getId() == Integer.parseInt(taskId))
                .findFirst()
                .orElse(null));
    }

    public static void markDone(String[] args) {
        String taskId = args[1];
        if (!Utils.isFileExists(PATH)) {
            System.out.println("Tasks file does not exist. create some tasks first.");
        }

        TaskManager taskManager = new TaskManager();
        List<Task> tasks = taskManager.loadTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks found to mark. Create some tasks first.");
            return;
        }
        if (taskId == null || taskId.trim().isEmpty()) {
            System.out.println("Invalid task ID provided for update.");
            return;
        }
        if (tasks.stream().filter(t -> t.getId() == Integer.parseInt(taskId)).toList().isEmpty()) {
            System.out.println("No task found with ID: " + taskId);
            return;
        }

        tasks.stream()
                .filter(task -> task.getId() == Integer.parseInt(taskId))
                .findFirst()
                .ifPresent(task -> {
                    task.setStatus("done");
                    task.setUpdatedAt(new java.util.Date());
                });

        try {
            taskManager.saveTasks(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Task with ID " + taskId + " has been marked as done.");
        TablePrinter.printSingleTask(tasks.stream()
                .filter(t -> t.getId() == Integer.parseInt(taskId))
                .findFirst()
                .orElse(null));
    }

    public static void manageListCommand(String[] args) {
        if (!Utils.isFileExists(PATH)) {
            System.out.println("Tasks file does not exist. create some tasks first.");
        }
        TaskManager taskManager = new TaskManager();
        List<Task> tasks = taskManager.loadTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found to mark. Create some tasks first.");
            return;
        }
        if (args.length < 2) {
            System.out.println("No additional parameters provided for list command. Listing all tasks.");
            TablePrinter.printMulitpleTasks(tasks);
            return;
        }

        switch (args[1]) {
            case "done" ->
                taskManager.getTasksByStatus("done");
            case "in-progress" ->
                taskManager.getTasksByStatus("in-progress");
            case "todo" ->
                taskManager.getTasksByStatus("todo");
            default ->
                System.out.println("Unknown list command: " + args[1]);
        }
    }
}
