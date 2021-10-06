package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.RoleDao;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.RoleDaoImpl;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.User;

import javax.servlet.http.HttpSession;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class LoginCommand extends CommandFactory {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {
        logger.log(Level.INFO, "Enter LoginCommand doGet()");
        HttpSession session = request.getSession();
        request.setAttribute("role", getRole(request));
        request.setAttribute("messages", session.getAttribute("messages"));
        logger.log(Level.INFO, "leave LoginCommand doGet()");
        return "WEB-INF/jsp/login.jsp";
    }

    @Override
    public String doPost() {
        logger.log(Level.INFO, "enter LoginCommand doPost()");
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        RoleDao roleDao = new RoleDaoImpl();
        User user = userDao.findByEmail(email);

        String message = request.getParameter("messages");
        if (message != null && message.equals("emailIncorrect")) {
            logger.log(Level.INFO, "Email not valid in login form");
            session.setAttribute("messages", "Incorrect email");
            return "controller?command=login";
        } else if (user == null) {
            logger.log(Level.INFO, "User with this email not found");
            session.setAttribute("messages", "User with this email not found");
            return "controller?command=login";
        } else if (!user.getPassword().equals(password)) {
            logger.log(Level.INFO, "User has incorrect password");
            session.setAttribute("messages", "User with this password not found");
            return "controller?command=login";
        } else {
            session.setAttribute("user", user);
            session.setAttribute("role", roleDao.findById(user.getRoleId()).getName());
            session.setAttribute("messages", "");
            session.setAttribute("emailErr", "");
            logger.log(Level.INFO, "The user has been authorized");
            return "controller?command=courses";
        }
    }
}
