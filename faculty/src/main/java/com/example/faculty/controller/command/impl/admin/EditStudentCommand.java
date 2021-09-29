package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Student;

import static com.example.faculty.controller.constant.Methods.getRole;

public class EditStudentCommand extends CommandFactory {

    UserDao userDao = new UserDaoImpl();

    @Override
    public String doGet() {

        request.setAttribute("student",
                userDao.findStudentById(Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("role", getRole(request));

        return "jsp/users/admin/editStudent.jsp";
    }

    @Override
    public String doPost() {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = userDao.findStudentById(id);
        student.setEnable(!student.isEnable());
        userDao.updateStudent(student);
        return "controller?command=students";
    }
}
