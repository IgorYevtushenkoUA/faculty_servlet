package com.example.faculty.controller.command.impl.teacher;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.StudentHasCourseDao;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.StudentHasCourseDaoImpl;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.StudentHasCourse;
import com.example.faculty.model.entity.User;

import javax.servlet.http.HttpSession;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class TeacherCourseInfoCommand extends CommandFactory {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {
        logger.log(Level.INFO, "Enter TeacherCourseInfoCommand doGet()");
        HttpSession session = request.getSession();
        int courseId = Integer.parseInt(request.getParameter("id"));
        UserDao userDao = new UserDaoImpl();

        request.setAttribute("id", courseId);
        request.setAttribute("studentDto", userDao.findAllEnrolledStudentToCourse(courseId));
        request.setAttribute("role", getRole(request));

        logger.log(Level.INFO, "Leave TeacherCourseInfoCommand doGet()");

        return "WEB-INF/jsp/users/teacher/courseInfo.jsp";
    }

    @Override
    public String doPost() {
        logger.log(Level.INFO, "Enter TeacherCourseInfoCommand doPost()");

        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int mark = Integer.parseInt(request.getParameter("mark"));

        StudentHasCourseDao studentHasCourseDao = new StudentHasCourseDaoImpl();
        studentHasCourseDao.update(mark, studentId, courseId);
        // here update student -> update mark in student

        logger.log(Level.INFO, "Leave TeacherCourseInfoCommand doPost()");
        return "controller?command=teacher-course&id=" + courseId;
    }

}
