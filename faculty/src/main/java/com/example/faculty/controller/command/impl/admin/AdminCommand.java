package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;

import static com.example.faculty.controller.constant.Methods.getRole;

public class AdminCommand extends CommandFactory {

    @Override
    public String doGet() {
        request.setAttribute("role", getRole(request));
        return "WEB-INF/jsp/users/admin/mainPage.jsp";
    }

    @Override
    public String doPost() {
        return null;
    }
}
