package com.example.faculty.model.entity;

import java.util.Objects;

public class Student extends User {


    private int courseNum;
    private boolean enable;

    public Student() {
        super();
    }

    public Student(Integer id, String firstName, String secondName, String lastName, String email, String password) {
        super(id, firstName, secondName, lastName, email, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return getCourseNum() == student.getCourseNum() && isEnable() == student.isEnable();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCourseNum(), isEnable());
    }

    @Override
    public String toString() {
        return "Student{\n" + super.toString() +
                "\ncourseNum=" + courseNum +
                ", enable=" + enable +
                '}';
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
