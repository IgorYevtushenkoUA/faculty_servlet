package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Student;
import com.example.faculty.model.entity.User;

public class CreateStudentCommand extends CommandFactory {
    @Override
    public String doGet() {
        return "jsp/users/admin/createStudent.jsp";
    }

    @Override
    public String doPost() {
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

        return "controller?command=student-create";
    }
}
