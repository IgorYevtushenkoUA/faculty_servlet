package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.constant.PageConstants;
import com.example.faculty.controller.constant.PathConstants;
import com.example.faculty.dao.model.RoleDao;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.RoleDaoImpl;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.User;

import javax.servlet.http.HttpSession;

public class LoginCommand extends CommandFactory {

    @Override
    public String doGet() {
        return PageConstants.LOGIN;
    }

    @Override
    public String doPost() {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        RoleDao roleDao = new RoleDaoImpl();
        User user = userDao.findByEmail(email);
        if (user == null) {
            System.out.println("user with this email not found");
            return null;
        } else if (!user.getPassword().equals(password)) {
            System.out.println("user with this password not found");
            return null;
        } else {
            System.out.println("");
            session.setAttribute("user", user);
            session.setAttribute("role", roleDao.findById(user.getRoleId()).getName());
            return PathConstants.REDIRECT_TO_COURSES;
        }
    }
}
