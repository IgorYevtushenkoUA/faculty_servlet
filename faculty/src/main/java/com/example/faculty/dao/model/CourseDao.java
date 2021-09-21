package com.example.faculty.dao.model;

import com.example.faculty.model.entity.Course;

import java.util.List;

public interface CourseDao {
    List<Course> findAll();

    Course findById(int id);

    boolean addCourse(Course course);

    void updateCourse(Course course);

    List<Course> findCourseByParams(List<String> courseName, List<Integer> duration, List<Integer> capacity, List<String> topic, List<Integer> teacherId);

    List<Course> findAllTeachersCourses(int teacherId);

    boolean deleteCourseById(int id);

    boolean addTeacherToCourse(int courseId, int teacherId);

    boolean deleteTeacherFromCourse(int teacherId, int courseId);

    List<Course> findAllStudentCoursesByType(String type);

}
