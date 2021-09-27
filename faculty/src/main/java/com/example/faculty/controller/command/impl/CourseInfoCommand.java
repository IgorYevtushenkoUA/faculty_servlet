package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.TopicDao;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.dao.model.impl.TopicDaoImpl;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.dto.StudentShortInfoDto;
import com.example.faculty.model.entity.Course;
import com.example.faculty.model.entity.Student;
import com.example.faculty.model.entity.Teacher;

import java.util.List;

public class CourseInfoCommand extends CommandFactory {
    @Override
    public String doGet() {

        int courseId = Integer.parseInt(request.getParameter("courseId"));

        CourseDao courseDao = new CourseDaoImpl();
        UserDao userDao = new UserDaoImpl();
        TopicDao topicDao = new TopicDaoImpl();
        Course course = courseDao.findById(courseId);

        request.setAttribute("course", course);
        request.setAttribute("topic", topicDao.findById(course.getTopicId()));
        request.setAttribute("studentDto", userDao.findAllEnrolledStudentToCourse(courseId));

        //        Teacher teacher = course.getTeacherId() != null
//                ? userDao.findTeacherById(course.getTeacherId())
//                : null;


        return "jsp/courseInfo.jsp";
    }

    @Override
    public String doPost() {
        return null;
    }
}
