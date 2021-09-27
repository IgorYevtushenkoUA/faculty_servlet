package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;

public class AdminCommand extends CommandFactory {
    @Override
    public String doGet() {
        return "jsp/users/admin/mainPage.jsp";
    }

    @Override
    public String doPost() {
        return null;
    }
}
