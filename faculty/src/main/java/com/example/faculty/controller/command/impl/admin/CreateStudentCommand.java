package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Student;
import com.example.faculty.model.entity.User;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class CreateStudentCommand extends CommandFactory {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {
        logger.log(Level.INFO, "enter CreateStudentCommand doGet()");
        request.setAttribute("role", getRole(request));
        logger.log(Level.INFO, "leave CreateStudentCommand doGet()");
        return "WEB-INF/jsp/users/admin/createStudent.jsp";
    }

    @Override
    public String doPost() {
        logger.log(Level.INFO, "enter CreateStudentCommand doPost()");
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setSecondName(request.getParameter("secondName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setRoleId(3);

        user = userDao.saveUser(user);
        Student student = new Student(user, Integer.parseInt(request.getParameter("courseNum")));
        userDao.saveStudent(student);
        logger.log(Level.INFO, "Admin has created new student");
        logger.log(Level.INFO, "leave CreateStudentCommand doPost()");

        return "controller?command=student-create";
    }
}
