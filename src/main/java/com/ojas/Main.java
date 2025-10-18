package com.ojas;

public class Main {

    public static void manageArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("No command provided.");
            return;
        }
        String command = args[0];
        switch (command) {
            case "add" -> {
                if (args.length < 2) {
                    System.out.println("Description required for adding a task.");
                    return;
                }

                CommandOperations.add(args);
            }
            case "update" -> {
                if (args.length < 3) {
                    System.out.println("Task ID and new description required for updating a task.");
                    return;
                }
                CommandOperations.update(args);
            }
            case "delete" -> {
                if (args.length < 2) {
                    System.out.println("Task ID required for deleting a task.");
                    return;
                }
                CommandOperations.delete(args);
            }
            case "mark-in-progress" -> {
                if (args.length < 2) {
                    System.out.println("Task ID required for marking a task as in-progress.");
                    return;
                }
                CommandOperations.markInProgress(args);
            }
            case "mark-done" -> {
                if (args.length < 2) {
                    System.out.println("Task ID required for marking a task as done.");
                    return;
                }
                CommandOperations.markDone(args);
            }
            case "list" ->
                CommandOperations.manageListCommand(args);
            default ->
                System.out.println("Unknown command: " + command);
        }
    }

    public static void main(String[] args) {
        manageArgs(args);

    }
}
