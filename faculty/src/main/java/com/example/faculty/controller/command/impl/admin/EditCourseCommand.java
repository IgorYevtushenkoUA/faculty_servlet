package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.TopicDao;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.dao.model.impl.TopicDaoImpl;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Course;

public class EditCourseCommand extends CommandFactory {

    CourseDao courseDao = new CourseDaoImpl();
    TopicDao topicDao = new TopicDaoImpl();
    UserDao userDao = new UserDaoImpl();

    @Override
    public String doGet() {

        int courseId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("course", courseDao.findById(courseId));
        request.setAttribute("topics", topicDao.findAll());
        request.setAttribute("teachers", userDao.findAllTeacher());
        return "jsp/users/admin/editCourse.jsp";
    }

    @Override
    public String doPost() {
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

        return "controller?command=courses";
    }
}
