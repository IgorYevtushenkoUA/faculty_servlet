package com.example.faculty.model.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class StudentHasCourse {

    private Student student;
    private Course course;
    private Integer mark;
    private Timestamp recordingTime;

    public StudentHasCourse() {
    }

    public StudentHasCourse(Student student, Course course, Integer mark, Timestamp recordingTime) {
        this.student = student;
        this.course = course;
        this.mark = mark;
        this.recordingTime = recordingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentHasCourse)) return false;
        StudentHasCourse that = (StudentHasCourse) o;
        return getStudent().equals(that.getStudent()) && getCourse().equals(that.getCourse()) && getMark().equals(that.getMark()) && getRecordingTime().equals(that.getRecordingTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudent(), getCourse(), getMark(), getRecordingTime());
    }

    @Override
    public String toString() {
        return "StudentHasCourse{" +
                "student=" + student +
                ", course=" + course +
                ", mark=" + mark +
                ", recordingTime=" + recordingTime +
                '}';
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Timestamp getRecordingTime() {
        return recordingTime;
    }

    public void setRecordingTime(Timestamp recordingTime) {
        this.recordingTime = recordingTime;
    }

}
