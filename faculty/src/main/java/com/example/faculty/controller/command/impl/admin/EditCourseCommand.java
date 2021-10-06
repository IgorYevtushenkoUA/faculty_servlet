package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.TopicDao;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.dao.model.impl.TopicDaoImpl;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Course;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class EditCourseCommand extends CommandFactory {

    CourseDao courseDao = new CourseDaoImpl();
    TopicDao topicDao = new TopicDaoImpl();
    UserDao userDao = new UserDaoImpl();
    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {

        logger.log(Level.INFO, "enter EditCourseCommand doGet()");

        request.setAttribute("course", courseDao.findById(Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("topics", topicDao.findAll());
        request.setAttribute("teachers", userDao.findAllTeacher());
        request.setAttribute("role", getRole(request));

        logger.log(Level.INFO, "leave EditCourseCommand doGet()");

        return "WEB-INF/jsp/users/admin/editCourse.jsp";
    }

    @Override
    public String doPost() {
        logger.log(Level.INFO, "enter EditCourseCommand doPost()");

        int id = Integer.parseInt(request.getParameter("id"));
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        int semesterStart = Integer.parseInt(request.getParameter("semesterStart"));
        int duration = Integer.parseInt(request.getParameter("semesterDuration"));
        String description = request.getParameter("description");
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        String name = request.getParameter("name");

        Course course = new Course(id, topicId, capacity, semesterStart, duration, description, teacherId, name);
        courseDao.updateCourse(course);

        logger.log(Level.INFO, "Admin edit course");
        logger.log(Level.INFO, "leave EditCourseCommand doPost()");

        return "controller?command=courses";
    }
}
