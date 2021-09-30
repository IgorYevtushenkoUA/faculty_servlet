package com.example.faculty.dao.model;


public interface StudentHasCourseDao {

    void update(int mark, int studentId, int courseId);

    void enrollStudent(int studentId, int courseId);

    void dropOutStudent(int studentId, int courseId);

    boolean isStudentEnrolled(int studentId, int courseId);

}
