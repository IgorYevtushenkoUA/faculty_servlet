package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.*;
import com.example.faculty.dao.model.impl.*;
import com.example.faculty.model.entity.Course;
import com.example.faculty.model.entity.User;
import com.example.faculty.model.enums.ROLE;

import javax.servlet.http.HttpSession;


public class CourseInfoCommand extends CommandFactory {

    CourseDao courseDao = new CourseDaoImpl();
    UserDao userDao = new UserDaoImpl();
    TopicDao topicDao = new TopicDaoImpl();
    RoleDao roleDao = new RoleDaoImpl();
    StudentHasCourseDao studentHasCourseDao = new StudentHasCourseDaoImpl();


    @Override
    public String doGet() {

        int courseId = Integer.parseInt(request.getParameter("courseId"));

        Course course = courseDao.findById(courseId);

        request.setAttribute("course", course);
        request.setAttribute("topic", topicDao.findById(course.getTopicId()));
        request.setAttribute("studentDto", userDao.findAllEnrolledStudentToCourse(courseId));
        request.setAttribute("role", getRole(courseId));
        //        Teacher teacher = course.getTeacherId() != null
//                ? userDao.findTeacherById(course.getTeacherId())
//                : null;

        return "WEB-INF/jsp/courseInfo.jsp";
    }

    @Override
    public String doPost() {
        String action = request.getParameter("action");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));

        if (action.equals("enroll")) {
            studentHasCourseDao.enrollStudent(studentId, courseId);
        } else if (action.equals("drop_out")) {
            studentHasCourseDao.dropOutStudent(studentId, courseId);
        }
        return "controller?command=course&courseId=" + courseId;
    }


    private String getRole(int courseId) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) return "ROLE_GUEST";
        String role = roleDao.findById(user.getRoleId()).getName();
        if (ROLE.ROLE_STUDENT.name().equals(role)) {
            request.setAttribute("studentId", user.getId());
            request.setAttribute("canEnroll", userDao.findStudentById(user.getId()).isEnable());
            request.setAttribute("doEnroll", studentHasCourseDao.isStudentEnrolled(user.getId(), courseId));
        }
        return role;
    }

}
