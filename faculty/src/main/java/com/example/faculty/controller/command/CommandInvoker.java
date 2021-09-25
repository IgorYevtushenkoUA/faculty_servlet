package com.example.faculty.controller.command;

import com.example.faculty.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {

    private static Map<String, CommandCreate> commands = new HashMap<>();

    static {
        // guest
        commands.put("login", LoginCommand::new);
        commands.put("register", RegisterCommand::new);
        commands.put("courses", ListCoursesCommand::new);
        commands.put("course", CourseInfoCommand::new);
        // student
        commands.put("student", StudentCommand::new);
        // teacher
        commands.put("teacher", TeacherCommand::new);
        // admin
        commands.put("admin", AdminCommand::new);
    }

    public static CommandFactory getCommand(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("unknownCommand").create();
        } else {
            return commands.get(commandName).create();
        }
    }
}
