package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.command.impl.LoginCommand;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class AdminCommand extends CommandFactory {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {
        logger.log(Level.INFO, "enter AdminCommand doGet()");
        request.setAttribute("role", getRole(request));
        logger.log(Level.INFO, "leave AdminCommand doGet()");
        return "WEB-INF/jsp/users/admin/mainPage.jsp";
    }

    @Override
    public String doPost() {
        return null;
    }
}
