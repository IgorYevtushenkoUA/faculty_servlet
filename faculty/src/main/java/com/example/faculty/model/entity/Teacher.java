package com.example.faculty.model.entity;

public class Teacher extends User {
    public Teacher(Integer id, String firstName, String secondName, String lastName, String email, String password, int roleId) {
        super(id, firstName, secondName, lastName, email, password, roleId);
    }

    public Teacher() {
        super();
    }

    public Teacher(User user){
        super(user);
    }

    @Override
    public String toString() {
        return "Teacher{\n" + super.toString() + "\n}";
    }
}
