package com.ojas;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void manageArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("No command provided.");
            return;
        }

        for(String arg:args){
            System.out.println("Argument: "+arg);
        }

        String command = args[0];
        switch (command) {
            case "add":
                // Handle add command
                if(args.length < 2) {
                    System.out.println("Description required for adding a task.");
                    return;
                }

                try {
                    CommandOperations.add(args);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case "update":
                // Handle update command
                if(args.length < 3) {
                    System.out.println("Task ID and new description required for updating a task.");
                    return;
                }
                CommandOperations.update(args);
                break;
            case "delete":
                // Handle delete command
                if(args.length < 2) {
                    System.out.println("Task ID required for deleting a task.");
                    return;
                }
                CommandOperations.delete(args);
                break;
            case "mark-in-progress":
                // Handle mark-in-progress command
                if(args.length < 2) {
                    System.out.println("Task ID required for marking a task as in-progress.");
                    return;
                }
                CommandOperations.markInProgress(args);
                break;
            case "mark-done":
                // Handle mark-done command
                if(args.length < 2) {
                    System.out.println("Task ID required for marking a task as done.");
                    return;
                }   
                CommandOperations.markDone(args);
                break;
            case "list":
                // Handle list command
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }

    public static void main(String[] args) {
        final Map<String, Integer> COMMANDS = Map.of(
                "add",
                1,
                "update",
                1,
                "delete",
                1,
                "mark-in-progress",
                1,
                "mark-done",
                1,
                "list",
                1
        );

        manageArgs(args);

        

        // TaskManager tm = new TaskManager();
        // tm.loadTasks();
    }
}
