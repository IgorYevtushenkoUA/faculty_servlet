package com.example.faculty.model.entity;

import com.example.faculty.dao.Identified;

import java.io.Serializable;

public class Teacher extends User implements Identified<Integer>, Serializable {
    public Teacher(Integer id, String firstName, String secondName, String lastName, String email, String password, int roleId) {
        super(id, firstName, secondName, lastName, email, password, roleId);
    }

    public Teacher() {
        super();
    }

    @Override
    public String toString() {
        return "Teacher{\n" + super.toString() + "\n}";
    }
}
