package com.example.faculty.dto;

import com.example.faculty.model.entity.Student;

import java.sql.Timestamp;

public class StudentShortInfoDto {

    private Student student;
    private Integer mark;
    private Timestamp recordingTime;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    @Override
    public String toString() {
        return "StudentShortInfoDto{" +
                "student=" + student +
                ", mark=" + mark +
                ", recordingTime=" + recordingTime +
                '}';
    }
}
