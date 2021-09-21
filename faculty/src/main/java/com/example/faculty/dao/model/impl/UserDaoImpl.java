package com.example.faculty.dao.model.impl;

import com.example.faculty.dao.model.UserDao;
import com.example.faculty.db.ConnectionPool;
import com.example.faculty.db.Queries;
import com.example.faculty.model.entity.Student;
import com.example.faculty.model.entity.Teacher;
import com.example.faculty.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserDaoImpl implements UserDao {

    public void prepareStatementForCreate(PreparedStatement stmt, User user) {
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
    public boolean save(User user) {
        return false;
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
