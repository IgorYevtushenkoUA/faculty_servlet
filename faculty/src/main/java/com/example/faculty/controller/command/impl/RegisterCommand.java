package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.constant.PageConstants;
import com.example.faculty.controller.constant.PathConstants;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Student;
import com.example.faculty.model.entity.User;

import javax.servlet.http.HttpSession;

import static com.example.faculty.controller.constant.Methods.getRole;

public class RegisterCommand extends CommandFactory {
    @Override
    public String doGet() {
        request.setAttribute("role", getRole(request));
        return PageConstants.REGISTER;
    }

    @Override
    public String doPost() {
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
        System.out.println(user);
        Student student = new Student(user, Integer.parseInt(course));
        userDao.saveStudent(student);
        System.out.println(student);
        return null;
    }
}
