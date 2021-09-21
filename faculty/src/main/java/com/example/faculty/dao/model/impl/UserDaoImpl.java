package com.example.faculty.dao.model.impl;

import com.example.faculty.dao.model.UserDao;
import com.example.faculty.db.ConnectionPool;
import com.example.faculty.db.Queries;
import com.example.faculty.model.entity.Student;
import com.example.faculty.model.entity.Teacher;
import com.example.faculty.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserDaoImpl implements UserDao {

    public void prepareStatementForCreateUser(PreparedStatement stmt, User user) {
        try {
            int i = 1;
            stmt.setString(i++, user.getFirstName());
            stmt.setString(i++, user.getSecondName());
            stmt.setString(i++, user.getLastName());
            stmt.setString(i++, user.getEmail());
            stmt.setString(i++, user.getPassword());
            stmt.setInt(i++, user.getRoleId());
        } catch (SQLException e) {

        }
    }

    public void prepareStatementForCreateStudent(PreparedStatement stmt, Student student) {
        try {
            stmt.setInt(1, student.getId());
            stmt.setInt(2, student.getCourseNum());
            stmt.setBoolean(3, student.isEnable());
        } catch (SQLException e) {

        }
    }

    public void prepareStatementForCreateTeacher(PreparedStatement stmt, Teacher teacher) {
        try {
            stmt.setInt(1, teacher.getId());
        } catch (SQLException e) {

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
        }
        return list;
    }

    public void prepareStatementForUpdate(PreparedStatement stmt, User user) {
        try {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getSecondName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setInt(6, user.getRoleId());
        } catch (SQLException e) {
        }
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_USER_BY_EMAIL)) {
            stmt.setString(1, email);
            user = parseResultSet(stmt.executeQuery()).iterator().next();
        } catch (SQLException e) {
            System.out.println("SQLException e");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException e");
        }
        return user;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_USER_BY_ID)) {
            stmt.setInt(1, id);
            user = parseResultSet(stmt.executeQuery()).iterator().next();
        } catch (SQLException e) {
            System.out.println("SQLException e");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException e");
        }
        return user;
    }

    @Override
    synchronized public User saveUser(User user) {
        System.out.println("saveUser daoIml");
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForCreateUser(stmt, user);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(Math.toIntExact(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println("SQLException e");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException e");
        }
        return user;
    }

    @Override
    synchronized public Student saveStudent(Student student) {
        System.out.println("saveStudent daoIml + " + student);
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_STUDENT)) {
            prepareStatementForCreateStudent(stmt, student);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException e");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException e");
        }
        return student;
    }

    @Override
    synchronized  public Teacher saveTeacher(Teacher teacher) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_TEACHER, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForCreateTeacher(stmt, teacher);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException e");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException e");
        }
        return teacher;
    }

    @Override
    public List<Teacher> findAllTeachersByPIB(String name) {
        return null;
    }


    @Override
    public List<Teacher> findAllTeacher() {
        return null;
    }

    @Override
    public List<Student> findAllStudent() {
        return null;
    }

    @Override
    public Student findStudentInfoBydAndCourse(int id, int courseId) {
        return null;
    }

    @Override
    public Student findStudentById(int id) {
        return null;
    }

    @Override
    public List<Student> findAllStudentsByPIB(String name) {
        return null;
    }

    @Override
    public List<Student> findAllEnrolledStudentToCourse(int courseId) {
        return null;
    }
}
