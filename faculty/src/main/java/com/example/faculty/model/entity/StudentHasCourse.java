package com.example.faculty.model.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class StudentHasCourse {

    private int studentId;
    private int courseId;
    private int statusId;
    private Integer mark;
    private Timestamp recordingTime;


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
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
        return "StudentHasCourse{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", statusId=" + statusId +
                ", mark=" + mark +
                ", recordingTime=" + recordingTime +
                '}';
    }
}
