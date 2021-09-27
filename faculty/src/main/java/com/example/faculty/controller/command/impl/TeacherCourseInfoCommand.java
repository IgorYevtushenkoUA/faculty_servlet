package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.StudentHasCourseDao;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.StudentHasCourseDaoImpl;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.StudentHasCourse;
import com.example.faculty.model.entity.User;

import javax.servlet.http.HttpSession;

public class TeacherCourseInfoCommand extends CommandFactory {

    @Override
    public String doGet() {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int courseId = Integer.parseInt(request.getParameter("id"));
        UserDao userDao = new UserDaoImpl();

        request.setAttribute("id", courseId);
        request.setAttribute("studentDto", userDao.findAllEnrolledStudentToCourse(courseId));

        return "jsp/users/teacher/courseInfo.jsp";
    }

    @Override
    public String doPost() {

        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int mark = Integer.parseInt(request.getParameter("mark"));

        StudentHasCourseDao studentHasCourseDao = new StudentHasCourseDaoImpl();
        studentHasCourseDao.update(mark, studentId, courseId);
        // here update student -> update mark in student

        return "controller?command=teacher-course&id=" + courseId;
    }

}
