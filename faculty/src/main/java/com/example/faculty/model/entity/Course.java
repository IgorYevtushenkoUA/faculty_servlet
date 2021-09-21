package com.example.faculty.model.entity;

import com.example.faculty.dao.Identified;

import java.io.Serializable;
import java.util.Objects;

public class Course implements Serializable {

    private Integer id;
    private Topic topic;
    private int capacity;
    private int semesterStart;
    private int semesterDuration;
    private String description;
    private Teacher teacher;
    private String name;

    public Course() {
    }

    public Course(Topic topic, int capacity, int semesterStart, int semesterDuration, String description, Teacher teacher, String name) {
        this.topic = topic;
        this.capacity = capacity;
        this.semesterStart = semesterStart;
        this.semesterDuration = semesterDuration;
        this.description = description;
        this.teacher = teacher;
        this.name = name;
    }

    public Course(Integer id, Topic topic, int capacity, int semesterStart, int semesterDuration, String description, Teacher teacher, String name) {
        this.id = id;
        this.topic = topic;
        this.capacity = capacity;
        this.semesterStart = semesterStart;
        this.semesterDuration = semesterDuration;
        this.description = description;
        this.teacher = teacher;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", topic=" + topic +
                ", capacity=" + capacity +
                ", semesterStart=" + semesterStart +
                ", semesterDuration=" + semesterDuration +
                ", description='" + description + '\'' +
                ", teacher=" + teacher +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getId() == course.getId() && getCapacity() == course.getCapacity() && getSemesterStart() == course.getSemesterStart() && getSemesterDuration() == course.getSemesterDuration() && getTopic().equals(course.getTopic()) && getDescription().equals(course.getDescription()) && getTeacher().equals(course.getTeacher()) && getName().equals(course.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTopic(), getCapacity(), getSemesterStart(), getSemesterDuration(), getDescription(), getTeacher(), getName());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSemesterStart() {
        return semesterStart;
    }

    public void setSemesterStart(int semesterStart) {
        this.semesterStart = semesterStart;
    }

    public int getSemesterDuration() {
        return semesterDuration;
    }

    public void setSemesterDuration(int semesterDuration) {
        this.semesterDuration = semesterDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
