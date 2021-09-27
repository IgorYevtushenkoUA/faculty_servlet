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
        commands.put("teacher-course", TeacherCourseInfoCommand::new);
        // admin
        commands.put("admin", AdminCommand::new);

        commands.put("course-create", AdminCommand::new);
        commands.put("teacher-create", AdminCommand::new);
        commands.put("student-create", AdminCommand::new);

        commands.put("admin-courses", AdminCommand::new);
        commands.put("admin-teachers", AdminCommand::new);
        commands.put("admin-students", AdminCommand::new);

        commands.put("course-edit", AdminCommand::new);
        commands.put("teacher-edit", AdminCommand::new);
        commands.put("student-edit", AdminCommand::new);
        commands.put("", AdminCommand::new);
    }

    public static CommandFactory getCommand(String commandName) {
        System.out.println("-----------------------------------------------------");
        System.out.println("COMMAND NAME : [" + commandName + "]");
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("unknownCommand").create();
        } else {
            return commands.get(commandName).create();
        }
    }
}
