package com.example.faculty.model.entity;

import java.util.Objects;

public class Course{

    private Integer id;
    private int topicId;
    private int capacity;
    private int semesterStart;
    private int semesterDuration;
    private String description;
    private Integer teacherId;
    private String name;

    public Course() {
    }

    public Course(int topicId, int capacity, int semesterStart, int semesterDuration, String description, Integer teacherId, String name) {
        this.topicId = topicId;
        this.capacity = capacity;
        this.semesterStart = semesterStart;
        this.semesterDuration = semesterDuration;
        this.description = description;
        this.teacherId = teacherId;
        this.name = name;
    }

    public Course(Integer id, Topic topic, int capacity, int semesterStart, int semesterDuration, String description, int teacherId, String name) {
        this.id = id;
        this.topicId = topicId;
        this.capacity = capacity;
        this.semesterStart = semesterStart;
        this.semesterDuration = semesterDuration;
        this.description = description;
        this.teacherId = teacherId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", topicId=" + topicId +
                ", capacity=" + capacity +
                ", semesterStart=" + semesterStart +
                ", semesterDuration=" + semesterDuration +
                ", description='" + description + '\'' +
                ", teacherId=" + teacherId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getId() == course.getId() && getCapacity() == course.getCapacity() && getSemesterStart() == course.getSemesterStart() && getSemesterDuration() == course.getSemesterDuration() && getTopicId() == course.getTopicId() && getDescription().equals(course.getDescription()) && getTeacherId().equals(course.getTeacherId()) && getName().equals(course.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTopicId(), getCapacity(), getSemesterStart(), getSemesterDuration(), getDescription(), getTeacherId(), getName());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTopicId() {
        return this.topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
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

    public Integer getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
