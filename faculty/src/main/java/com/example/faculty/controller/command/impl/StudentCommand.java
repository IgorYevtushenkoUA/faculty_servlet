package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.TopicDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.dao.model.impl.TopicDaoImpl;
import com.example.faculty.dto.StudentCourseInfoDto;
import com.example.faculty.model.entity.User;
import com.example.faculty.model.enums.STATUS;

import javax.servlet.http.HttpSession;
import java.util.List;

public class StudentCommand extends CommandFactory {
    @Override
    public String doGet() {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String statusType = request.getParameter("type") == null ? STATUS.NOT_STARTED.name() : request.getParameter("type");
        CourseDao courseDao = new CourseDaoImpl();
        List<StudentCourseInfoDto> courses = courseDao.findAllStudentCoursesByType(user.getId(), statusType);

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(courses);
        System.out.println("type = " + statusType);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        request.setAttribute("courses", courses);
        request.setAttribute("type", statusType);

        return "jsp/users/student/mainPage.jsp";
    }

    @Override
    public String doPost() {
        return null;
    }
}
