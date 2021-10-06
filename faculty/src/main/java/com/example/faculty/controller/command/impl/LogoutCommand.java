package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;

import javax.servlet.http.HttpSession;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class LogoutCommand extends CommandFactory {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {

        logger.log(Level.INFO, "Enter LogoutCommand doGet()");

        HttpSession session = request.getSession(false);
        request.setAttribute("role", getRole(request));
        if (session != null) {
            session.invalidate();
        }
        logger.log(Level.INFO, "Leave LogoutCommand doGet()");
        return "controller?command=courses";
    }

    @Override
    public String doPost() {
        return doGet();
    }

}
