package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.constant.PathConstants;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.User;

import javax.servlet.http.HttpSession;

public class LoginCommand extends CommandFactory {

    @Override
    public String doGet() {
        return PathConstants.LOGIN_PAGE;
    }

    @Override
    public String doPost() {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao dao = new UserDaoImpl();
        User user = dao.findByEmail(email);
        if (user == null) {
            System.out.println("user with this email not found");
//            return PathConstants.LOGIN_PAGE;
            return null;
        } else if (!user.getPassword().equals(password)) {
            System.out.println("user with this password not found");
//            return PathConstants.LOGIN_PAGE;
            return null;
        } else {
            return PathConstants.COURSES_PAGE;
        }
    }
}
