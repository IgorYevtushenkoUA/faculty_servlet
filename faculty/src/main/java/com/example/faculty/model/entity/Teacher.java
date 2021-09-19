package com.example.faculty.model.entity;

public class Teacher extends User {
    public Teacher(Integer id, String firstName, String secondName, String lastName, String email, String password) {
        super(id, firstName, secondName, lastName, email, password);
    }

    public Teacher() {
        super();
    }

    @Override
    public String toString() {
        return "Teacher{\n" + super.toString() + "\n}";
    }
}
