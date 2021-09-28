package com.example.faculty.dao.model;

import com.example.faculty.dto.StudentShortInfoDto;
import com.example.faculty.model.entity.Student;
import com.example.faculty.model.entity.Teacher;
import com.example.faculty.model.entity.User;

import java.util.List;

public interface UserDao {

    User findByEmail(String email);

    User findById(int id);

    User saveUser(User user);

    Student saveStudent(Student student);

    Teacher saveTeacher(Teacher teacher);

    List<Teacher> findAllTeachersByPIB(String name);

    List<Teacher> findAllTeacher();

    List<Student> findAllStudent();

    Student findStudentInfoBydAndCourse(int id, int courseId);

    Student findStudentById(int id);

    List<Student> findAllStudentsByPIB(String name);

    List<StudentShortInfoDto> findAllEnrolledStudentToCourse(int courseId);

    void updateStudent(Student student);

    void updateTeacher(Teacher teacher);

}
