package com.ojas;

import java.util.Map;

public class Main {

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

        TaskManager tm = new TaskManager();
        tm.loadTasks();
    }
}
