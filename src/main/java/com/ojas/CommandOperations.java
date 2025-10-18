package com.ojas;

import java.io.IOException;
import java.util.List;

public class CommandOperations {

    public static final String PATH = "./src/main/java/com/ojas/tasks.json";
    public static boolean isTaskDescriptionVaild(String description){
        return (description == null || description.trim().isEmpty());
    }


    public static void add(String[] args) throws IOException{
        String description = args[1];
        System.out.println("Adding task with description: " + description);

        if(isTaskDescriptionVaild(description)){
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
        // System.out.println("Loaded Tasks: " + tasks.size());

        int newTaskId = tasks.size() + 1;
        String status = "todo";
        Task newTask = new Task(newTaskId, description, status, new java.util.Date(), new java.util.Date());
        tasks.add(newTask);
        taskManager.saveTasks(tasks);
        System.out.println("New Task Created: " + newTask);
        
    }

   public static void update(String[] args){
       String taskId = args[1];
       String newDescription = args[2];

       if (!Utils.isFileExists(PATH)) {
           System.out.println("Tasks file does not exist. create some tasks first.");
       }
       TaskManager taskManager = new TaskManager();
       List<Task> tasks = taskManager.loadTasks();
       if(taskId == null || taskId.trim().isEmpty() || tasks.size() < Integer.parseInt(taskId)){
           System.out.println("Invalid task ID provided for update.");
           return;
       }
       if(tasks.isEmpty()){
           System.out.println("No tasks found to update. Create some tasks first.");
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
   } 
   public static void delete(String[] args){
       String taskId = args[1];
       System.out.println("Deleting task with ID: " + taskId);
   } 
    
}
