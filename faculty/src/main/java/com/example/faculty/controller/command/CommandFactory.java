package com.example.faculty.controller.command;

import com.example.faculty.controller.constant.PageConstants;
import com.example.faculty.model.enums.ACTION;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public abstract class CommandFactory implements ICommand {

    public ServletContext context;
    public HttpServletRequest request;
    public HttpServletResponse response;
    public ACTION action;

    public void init(ServletContext context,
                     HttpServletRequest request,
                     HttpServletResponse response,
                     ACTION action) {
        this.context = context;
        this.request = request;
        this.response = response;
        this.action = action;
    }

    @Override
    public final String execute() throws ServletException {
        if (action == null) {
            System.out.println("action is null");
            return PageConstants.COURSES;
        } else if (action == ACTION.GET) {
            return doGet();
        } else {
            return doPost();
        }
    }

    public abstract String doGet();

    public abstract String doPost();

}
