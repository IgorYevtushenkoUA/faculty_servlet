package com.example.faculty.controller.command;

import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.controller.command.impl.RegisterCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {

    private static Map<String, CommandCreate> commands = new HashMap<>();

    static {
        commands.put("login", LoginCommand::new);
        commands.put("register", RegisterCommand::new);
    }

    public static CommandFactory getCommand(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("unknownCommand").create();
        } else {
            return commands.get(commandName).create();
        }
    }
}
