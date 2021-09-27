package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.TopicDao;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.dao.model.impl.TopicDaoImpl;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Course;

public class CreateCourseCommand extends CommandFactory {
    @Override
    public String doGet() {

        TopicDao topicDao = new TopicDaoImpl();
        UserDao userDao = new UserDaoImpl();

        request.setAttribute("topics", topicDao.findAll());
        request.setAttribute("teachers", userDao.findAllTeacher());

        return "jsp/users/admin/createCourse.jsp";
    }

    @Override
    public String doPost() {

        CourseDao courseDao = new CourseDaoImpl();

        Course course = new Course();
        System.out.println("course :" + request.getParameter("course"));
        System.out.println("topicId :" + request.getParameter("topicId"));
        System.out.println("capacity :" + request.getParameter("capacity"));
        System.out.println("semester :" + request.getParameter("semesterStart"));
        System.out.println("duration :" + request.getParameter("duration"));
        System.out.println("description :" + request.getParameter("description"));
        System.out.println("teacherId :" + request.getParameter("teacherId"));
        course.setName(request.getParameter("course"));
        course.setTopicId(Integer.parseInt(request.getParameter("topicId")));
        course.setCapacity(Integer.parseInt(request.getParameter("capacity")));
        course.setSemesterStart(Integer.parseInt(request.getParameter("semesterStart")));
        course.setSemesterDuration(Integer.parseInt(request.getParameter("duration")));
        course.setDescription(request.getParameter("description"));
        course.setTeacherId(Integer.parseInt(request.getParameter("teacherId")));
        courseDao.addCourse(course);

        return "controller?command=course-create";
    }
}
