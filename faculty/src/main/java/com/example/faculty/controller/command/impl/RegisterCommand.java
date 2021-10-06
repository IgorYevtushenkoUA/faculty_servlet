package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Student;
import com.example.faculty.model.entity.User;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class RegisterCommand extends CommandFactory {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {
        logger.log(Level.INFO, "Enter RegisterCommand doGet()");
        request.setAttribute("role", getRole(request));
        logger.log(Level.INFO, "Leave RegisterCommand doGet()");
        return "WEB-INF/jsp/registration.jsp";
    }

    @Override
    public String doPost() {
        logger.log(Level.INFO, "Enter RegisterCommand doPost()");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String secondName = request.getParameter("secondName");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        String password = request.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        if (firstName == null || lastName == null || secondName == null || email == null || course == null || password == null) {
            System.out.println("not entered all fields");
            return null;
        }

        User user = new User(firstName, lastName, secondName, email, password, 3);
        user = userDao.saveUser(user);
        Student student = new Student(user, Integer.parseInt(course));
        userDao.saveStudent(student);
        logger.log(Level.INFO, "Leave RegisterCommand doPost()");
        return "controller?command=login";
    }
}
