package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Student;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class ListStudentCommand extends CommandFactory {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {
        logger.log(Level.INFO, "Enter ListStudentCommand doGet()");

        List<Student> students = getStudents(request.getParameter("name"));
        request.setAttribute("students", students);
        request.setAttribute("role", getRole(request));

        logger.log(Level.INFO, "Leave ListStudentCommand doGet()");

        return "WEB-INF/jsp/users/admin/listStudent.jsp";
    }

    private List<Student> getStudents(String name) {
        UserDao userDao = new UserDaoImpl();
        return name == null || name.isEmpty()
                ? userDao.findAllStudent()
                : userDao.findAllStudentsByPIB(name);
    }

    @Override
    public String doPost() {
        return null;
    }
}
