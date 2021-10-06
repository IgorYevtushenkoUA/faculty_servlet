package com.example.faculty.dao.model.impl;

import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.db.ConnectionPool;
import com.example.faculty.db.Queries;
import com.example.faculty.dto.StudentShortInfoDto;
import com.example.faculty.model.entity.Student;
import com.example.faculty.model.entity.Teacher;
import com.example.faculty.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());
    private int i = 0;

    private void prepareStatementForCreateUser(PreparedStatement ps, User user) {
        i = 1;
        try {
            ps.setString(i++, user.getFirstName());
            ps.setString(i++, user.getSecondName());
            ps.setString(i++, user.getLastName());
            ps.setString(i++, user.getEmail());
            ps.setString(i++, user.getPassword());
            ps.setInt(i++, user.getRoleId());
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error in prepareStatementForCreateUser");
        }
    }

    private void prepareStatementForCreateStudent(PreparedStatement ps, Student student) {
        i = 1;
        try {
            ps.setInt(i++, student.getId());
            ps.setInt(i++, student.getCourseNum());
            ps.setBoolean(i++, true);
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in prepareStatementForCreateStudent");
        }
    }

    private void prepareStatementForUpdateStudent(PreparedStatement ps, Student student) {
        i = 1;
        try {
            ps.setBoolean(i++, student.isEnable());
            ps.setInt(i++, student.getId());
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in prepareStatementForUpdateStudent");
        }
    }

    private void prepareStatementForCreateTeacher(PreparedStatement stmt, Teacher teacher) {
        i = 1;
        try {
            stmt.setInt(i++, teacher.getId());
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in prepareStatementForCreateTeacher");
        }
    }

    public List<User> parseResultSet(ResultSet rs) {
        List<User> list = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setSecondName(rs.getString("second_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRoleId(rs.getInt("role_id"));
                list.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in parseResultSet");
        }
        return list;
    }

    public List<Student> parseStudentResultSet(ResultSet rs) {
        List<Student> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setFirstName(rs.getString("first_name"));
                student.setSecondName(rs.getString("second_name"));
                student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
                student.setCourseNum(rs.getInt("course_num"));
                student.setRoleId(rs.getInt("role_id"));
                student.setEnable(rs.getBoolean("enable"));
                list.add(student);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in parseStudentResultSet");
        }
        return list;
    }

    public List<StudentShortInfoDto> parseStudentDtoResultSet(ResultSet rs) {
        List<StudentShortInfoDto> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setFirstName(rs.getString("first_name"));
                student.setSecondName(rs.getString("second_name"));
                student.setLastName(rs.getString("last_name"));
                student.setCourseNum(rs.getInt("course_num"));

                StudentShortInfoDto studentDto = new StudentShortInfoDto();
                studentDto.setStudent(student);
                studentDto.setMark(rs.getInt("mark"));
                studentDto.setRecordingTime(rs.getTimestamp("recording_time"));
                list.add(studentDto);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in parseStudentDtoResultSet");
        }
        return list;
    }

    public List<Teacher> parseTeacherResultSetFor(ResultSet rs) {
        List<Teacher> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Teacher t = new Teacher();
                t.setId(rs.getInt("id"));
                t.setFirstName(rs.getString("first_name"));
                t.setSecondName(rs.getString("second_name"));
                t.setLastName(rs.getString("last_name"));
                t.setEmail(rs.getString("email"));
                t.setPassword(rs.getString("password"));
                t.setRoleId(rs.getInt("role_id"));
                list.add(t);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in parseTeacherResultSetFor");
        }
        return list;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_USER_BY_EMAIL)) {
            stmt.setString(1, email);
            user = parseResultSet(stmt.executeQuery()).iterator().next();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findByEmail");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findByEmail");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findByEmail");
        }
        return user;
    }

    @Override
    public User findById(int id) {
        User user = new User();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_USER_BY_ID)) {
            stmt.setInt(1, id);
            user = parseResultSet(stmt.executeQuery()).iterator().next();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findByEmail");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findByEmail");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findByEmail");
        }
        return user;
    }

    @Override
    synchronized public User saveUser(User user) {
        System.out.println("saveUser daoIml");
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForCreateUser(stmt, user);
            stmt.executeUpdate(); // check
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in saveUser");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in saveUser");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in saveUser");
        }
        return user;
    }

    @Override
    synchronized public Student saveStudent(Student student) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_STUDENT)) {
            prepareStatementForCreateStudent(stmt, student);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in saveStudent");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in saveStudent");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in saveStudent");
        }
        return student;
    }

    @Override
    synchronized public Teacher saveTeacher(Teacher teacher) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_TEACHER, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForCreateTeacher(stmt, teacher);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in saveTeacher");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in saveTeacher");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in saveTeacher");
        }
        return teacher;
    }

    @Override
    public List<Teacher> findAllTeachersByPIB(String name) {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_TEACHER_BY_PIB)) {
            ps.setString(1, name);
            teachers = parseTeacherResultSetFor(ps.executeQuery());
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findAllTeachersByPIB");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findAllTeachersByPIB");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findAllTeachersByPIB");
        }
        return teachers;
    }

    @Override
    public List<Teacher> findAllTeacher() {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_ALL_TEACHERS)) {
            teachers = parseTeacherResultSetFor(ps.executeQuery());
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findAllTeacher");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findAllTeacher");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findAllTeacher");
        }
        return teachers;
    }

    @Override
    public List<Student> findAllStudent() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_ALL_STUDENTS)) {
            students = parseStudentResultSet(ps.executeQuery());
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findAllStudent");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findAllStudent");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findAllStudent");
        }
        return students;
    }

    @Override
    public Student findStudentById(int id) {
        Student student = new Student();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_STUDENT_BY_ID)) {
            stmt.setInt(1, id);
            student = parseStudentResultSet(stmt.executeQuery()).iterator().next();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findStudentById");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findStudentById");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findStudentById");
        }
        return student;
    }

    @Override
    public List<Student> findAllStudentsByPIB(String name) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_STUDENT_BY_PIB)) {
            stmt.setString(1, name);
            students = parseStudentResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findAllStudentsByPIB");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findAllStudentsByPIB");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findAllStudentsByPIB");
        }
        return students;
    }

    @Override
    public List<StudentShortInfoDto> findAllEnrolledStudentToCourse(int courseId) {
        List<StudentShortInfoDto> students = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_ENROLLED_STUDENT_TO_COURSE)) {
            stmt.setInt(1, courseId);
            students = parseStudentDtoResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findAllEnrolledStudentToCourse");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findAllEnrolledStudentToCourse");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findAllEnrolledStudentToCourse");
        }
        return students;
    }

    @Override
    public void updateStudent(Student student) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.UPDATE_STUDENT)) {
            prepareStatementForUpdateStudent(ps, student);
            parseResultSet(ps.executeQuery());
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in updateStudent");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in updateStudent");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in updateStudent");
        }
    }

}
