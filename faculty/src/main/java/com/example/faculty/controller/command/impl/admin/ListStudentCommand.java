package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Student;

import java.util.List;

public class ListStudentCommand extends CommandFactory {
    @Override
    public String doGet() {
        List<Student> students = getStudents(request.getParameter("name"));

        request.setAttribute("students", students);
        return "jsp/users/admin/listStudent.jsp";
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