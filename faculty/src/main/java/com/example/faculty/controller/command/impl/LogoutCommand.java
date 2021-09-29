package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;

import javax.servlet.http.HttpSession;

import static com.example.faculty.controller.constant.Methods.getRole;

public class LogoutCommand extends CommandFactory {

    @Override
    public String doGet() {
        HttpSession session = request.getSession(false);
        request.setAttribute("role", getRole(request));
        if (session != null) {
            session.invalidate();
        }
        return "controller?command=courses";
    }

    @Override
    public String doPost() {
        return doGet();
    }

}
