package com.example.faculty.controller.command.impl.teacher;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.model.entity.User;

import javax.servlet.http.HttpSession;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class TeacherCommand extends CommandFactory {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {
        logger.log(Level.INFO, "Enter TeacherCommand doGet()");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CourseDao courseDao = new CourseDaoImpl();

        request.setAttribute("teacher", user);
        request.setAttribute("courses", courseDao.findAll());
        request.setAttribute("role", getRole(request));

        logger.log(Level.INFO, "Leave TeacherCommand doGet()");

        return "WEB-INF/jsp/users/teacher/mainPage.jsp";
    }

    @Override
    public String doPost() {
        return null;
    }
}
