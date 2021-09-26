package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;

public class CourseInfoCommand extends CommandFactory {
    @Override
    public String doGet() {
        System.out.println("it is page of courseInfo");
        return "jsp/courseInfo.jsp";
    }

    @Override
    public String doPost() {
        return null;
    }
}
