package com.example.faculty.dto;

public class StudentCourseInfoDto {

    private int courseId;
    private String courseName;
    private Integer mark;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "StudentCourseInfoDto{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", mark=" + mark +
                '}';
    }
}
