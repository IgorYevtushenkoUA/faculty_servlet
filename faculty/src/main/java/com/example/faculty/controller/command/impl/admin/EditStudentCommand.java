package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Student;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class EditStudentCommand extends CommandFactory {

    UserDao userDao = new UserDaoImpl();
    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {

        logger.log(Level.INFO, "enter EditStudentCommand doGet()");
        request.setAttribute("student",
                userDao.findStudentById(Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("role", getRole(request));

        logger.log(Level.INFO, "leave EditStudentCommand doGet()");
        return "WEB-INF/jsp/users/admin/editStudent.jsp";
    }

    @Override
    public String doPost() {
        logger.log(Level.INFO, "enter EditStudentCommand doPost()");
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = userDao.findStudentById(id);
        student.setEnable(!student.isEnable());
        userDao.updateStudent(student);
        logger.log(Level.INFO, "Admin edit student");
        logger.log(Level.INFO, "leave EditStudentCommand doPost()");
        return "controller?command=students";
    }
}
