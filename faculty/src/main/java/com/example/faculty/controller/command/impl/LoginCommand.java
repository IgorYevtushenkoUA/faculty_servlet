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

import static com.example.faculty.controller.constant.Methods.getRole;

public class LoginCommand extends CommandFactory {

    @Override
    public String doGet() {
        HttpSession session = request.getSession();
        request.setAttribute("role", getRole(request));
        request.setAttribute("messages", session.getAttribute("messages"));
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

        System.out.println("Messages-Emailerr :" + request.getAttribute("messages"));
        System.out.println("Messages-Emailerr :" + request.getParameter("messages"));
        String message = request.getParameter("messages");
        if ( message != null && message.equals("emailIncorrect")) {
            session.setAttribute("messages", "Incorrect email");
            return "controller?command=login";
        } else if (user == null) {
            System.out.println("user with this email not found");
            session.setAttribute("messages", "User with this email not found");
            return "controller?command=login";
        } else if (!user.getPassword().equals(password)) {
            System.out.println("User with this password not found");
            session.setAttribute("messages", "User with this password not found");
            return "controller?command=login";
        } else {
            session.setAttribute("user", user);
            session.setAttribute("role", roleDao.findById(user.getRoleId()).getName());
            session.setAttribute("messages", "");
            session.setAttribute("emailErr", "");
            return PathConstants.REDIRECT_TO_COURSES;
        }
    }
}
