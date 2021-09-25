package com.example.faculty.dao.model;

import com.example.faculty.model.entity.Course;

import java.util.List;

public interface CourseDao {
    List<Course> findAll();

    Course findById(int id);

    List<Course> findByName(String name);

    Course addCourse(Course course);

    Course updateCourse(Course course);

    List<Course> findCourseByParams(String courseName, List<Integer> duration, List<Integer> capacity, List<Integer> topic, List<Integer> teacherId);

    List<Course> findAllTeachersCourses(int teacherId);

    boolean deleteCourseById(int id);

    boolean addTeacherToCourse(int courseId, int teacherId);

    Course deleteTeacherFromCourse(int teacherId, int courseId);

    List<Course> findAllStudentCoursesByType(String type);

}
