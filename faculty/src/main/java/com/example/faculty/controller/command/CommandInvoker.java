package com.example.faculty.controller.command;

import com.example.faculty.controller.command.impl.*;
import com.example.faculty.controller.command.impl.admin.*;
import com.example.faculty.controller.command.impl.student.StudentCommand;
import com.example.faculty.controller.command.impl.teacher.TeacherCommand;
import com.example.faculty.controller.command.impl.teacher.TeacherCourseInfoCommand;

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

        commands.put("course-create", CreateCourseCommand::new);
        commands.put("teacher-create", CreateTeacherCommand::new);
        commands.put("student-create", CreateStudentCommand::new);

        commands.put("students", ListStudentCommand::new);
        commands.put("teachers", ListTeacherCommand::new);

        commands.put("course-edit", EditCourseCommand::new);
        commands.put("teacher-edit", EditTeacherCommand::new);
        commands.put("student-edit", EditStudentCommand::new);
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
