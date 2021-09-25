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

        }
    }

    private void prepareStatementForCreateStudent(PreparedStatement stmt, Student student) {
        i = 1;
        try {
            stmt.setInt(i++, student.getId());
            stmt.setInt(i++, student.getCourseNum());
            stmt.setBoolean(i++, student.isEnable());
        } catch (SQLException e) {

        }
    }

    private void prepareStatementForCreateTeacher(PreparedStatement stmt, Teacher teacher) {
        i = 1;
        try {
            stmt.setInt(i++, teacher.getId());
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

    public List<Teacher> parseResultSetForTeacher(ResultSet rs) {
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
        }
        return list;
    }


    // todo here code is duplicate with create [think]
    public void prepareStatementForUpdate(PreparedStatement ps, User user) {
        i = 1;
        try {
            ps.setString(i++, user.getFirstName());
            ps.setString(i++, user.getSecondName());
            ps.setString(i++, user.getLastName());
            ps.setString(i++, user.getEmail());
            ps.setString(i++, user.getPassword());
            ps.setInt(i++, user.getRoleId());
        } catch (SQLException e) {
        }
    }

    @Override
    public User findByEmail(String email) {
        User user = new User();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_USER_BY_EMAIL)) {
            stmt.setString(1, email);
            user = parseResultSet(stmt.executeQuery()).iterator().next();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
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
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
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
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
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
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
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
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return teacher;
    }

    @Override
    public List<Teacher> findAllTeachersByPIB(String name) {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_TEACHER_BY_PIB)) {
            ps.setString(1, name);
            teachers =  parseResultSetForTeacher(ps.executeQuery());
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return teachers;
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
