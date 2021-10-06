package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Teacher;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class ListTeacherCommand extends CommandFactory {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {
        logger.log(Level.INFO, "Enter ListTeacherCommand doGet()");

        request.setAttribute("teachers", getTeachers(request.getParameter("name")));
        request.setAttribute("role", getRole(request));

        logger.log(Level.INFO, "Leave ListTeacherCommand doGet()");

        return "WEB-INF/jsp/users/admin/listTeacher.jsp";
    }

    private List<Teacher> getTeachers(String name) {
        UserDao userDao = new UserDaoImpl();
        return name == null || name.isEmpty()
                ? userDao.findAllTeacher()
                : userDao.findAllTeachersByPIB(name);
    }

    @Override
    public String doPost() {
        return null;
    }
}
