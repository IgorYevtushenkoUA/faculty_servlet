package com.example.faculty.controller.command.impl.teacher;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.model.entity.User;

import javax.servlet.http.HttpSession;

public class TeacherCommand extends CommandFactory {

    @Override
    public String doGet() {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CourseDao courseDao = new CourseDaoImpl();

        request.setAttribute("teacher", user);
        request.setAttribute("courses", courseDao.findAll());

        return "jsp/users/teacher/mainPage.jsp";
    }

    @Override
    public String doPost() {
        return null;
    }
}
