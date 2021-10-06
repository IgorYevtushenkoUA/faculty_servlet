package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Teacher;
import com.example.faculty.model.entity.User;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class CreateTeacherCommand extends CommandFactory {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {
        logger.log(Level.INFO, "enter  doGet()");
        request.setAttribute("role", getRole(request));
        logger.log(Level.INFO, "leave CreateTeacherCommand doGet()");
        return "WEB-INF/jsp/users/admin/createTeacher.jsp";
    }

    @Override
    public String doPost() {
        logger.log(Level.INFO, "enter CreateTeacherCommand doPost()");
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setSecondName(request.getParameter("secondName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setRoleId(2);

        user = userDao.saveUser(user);
        Teacher teacher = new Teacher(user);
        userDao.saveTeacher(teacher);

        logger.log(Level.INFO, "Admin create new Teacher");
        logger.log(Level.INFO, "enter CreateTeacherCommand doPost()");

        return "controller?command=teacher-create";
    }
}
