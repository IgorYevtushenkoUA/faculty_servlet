package com.example.faculty.controller.command.impl.student;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.dto.StudentCourseInfoDto;
import com.example.faculty.model.entity.User;
import com.example.faculty.model.enums.STATUS;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class StudentCommand extends CommandFactory {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {
        logger.log(Level.INFO, "Enter StudentCommand doGet()");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String statusType = request.getParameter("type") == null ? STATUS.NOT_STARTED.name() : request.getParameter("type");
        CourseDao courseDao = new CourseDaoImpl();
        List<StudentCourseInfoDto> courses = courseDao.findAllStudentCoursesByType(user.getId(), statusType);

        request.setAttribute("courses", courses);
        request.setAttribute("type", statusType);
        request.setAttribute("role", getRole(request));

        logger.log(Level.INFO, "Leave StudentCommand doGet()");

        return "WEB-INF/jsp/users/student/mainPage.jsp";
    }

    @Override
    public String doPost() {
        return null;
    }
}
