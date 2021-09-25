package com.example.faculty.controller.command;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.command.CommandInvoker;
import com.example.faculty.controller.constant.PageConstants;
import com.example.faculty.model.enums.ACTION;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "controller", value = "/")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        process(request, response, ACTION.GET);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        process(request, response, ACTION.POST);
    }

    private void process(HttpServletRequest request, HttpServletResponse response, ACTION action) {

        CommandFactory command = CommandInvoker.getCommand(request.getParameter("command"));
        command.init(getServletContext(), request, response, action);

        try {
            String path = command.execute();
            request.getSession().setAttribute("last_command", command);
            if (path == null) {
                response.sendRedirect(PageConstants.COURSES);
            } else if (action == ACTION.GET) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(path);
            }
        } catch (ServletException e) {
        } catch (IOException e) {
        }
    }
}