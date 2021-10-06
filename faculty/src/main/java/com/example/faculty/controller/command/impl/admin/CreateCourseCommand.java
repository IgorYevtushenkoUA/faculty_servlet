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

public class CreateCourseCommand extends CommandFactory {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {

        logger.log(Level.INFO, "enter CreateCourseCommand doGet()");
        TopicDao topicDao = new TopicDaoImpl();
        UserDao userDao = new UserDaoImpl();

        request.setAttribute("topics", topicDao.findAll());
        request.setAttribute("teachers", userDao.findAllTeacher());
        request.setAttribute("role", getRole(request));
        logger.log(Level.INFO, "leave CreateCourseCommand doGet()");
        return "WEB-INF/jsp/users/admin/createCourse.jsp";
    }

    @Override
    public String doPost() {
        logger.log(Level.INFO, "enter CreateCourseCommand doPost()");

        CourseDao courseDao = new CourseDaoImpl();

        Course course = new Course();
        course.setName(request.getParameter("course"));
        course.setTopicId(Integer.parseInt(request.getParameter("topicId")));
        course.setCapacity(Integer.parseInt(request.getParameter("capacity")));
        course.setSemesterStart(Integer.parseInt(request.getParameter("semesterStart")));
        course.setSemesterDuration(Integer.parseInt(request.getParameter("duration")));
        course.setDescription(request.getParameter("description"));
        course.setTeacherId(Integer.parseInt(request.getParameter("teacherId")));
        courseDao.addCourse(course);

        logger.log(Level.INFO, "Admin has created new course");
        logger.log(Level.INFO, "leave CreateCourseCommand doPost()");

        return "controller?command=course-create";
    }
}
