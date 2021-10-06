package com.example.faculty.dao.model.impl;

import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.StudentHasCourseDao;
import com.example.faculty.db.ConnectionPool;
import com.example.faculty.db.Queries;
import com.example.faculty.model.entity.StudentHasCourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentHasCourseDaoImpl implements StudentHasCourseDao {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    private List<StudentHasCourse> parseResultSet(ResultSet rs) {
        List<StudentHasCourse> list = new ArrayList<>();
        try {
            while (rs.next()) {
                StudentHasCourse shc = new StudentHasCourse();
                shc.setStudentId(rs.getInt("student_id"));
                shc.setCourseId(rs.getInt("course_id"));
                shc.setStatusId(rs.getInt("status_id"));
                shc.setMark(rs.getInt("mark"));
                shc.setRecordingTime(rs.getTimestamp("recording_time"));
                list.add(shc);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error in parseResultSet");
        }
        return list;
    }

    @Override
    public void update(int mark, int studentId, int courseId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.UPDATE_STUDENT_HAS_COURSE)) {
            ps.setInt(1, mark);
            ps.setInt(2, studentId);
            ps.setInt(3, courseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in update");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Error in update");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in update");
        }
    }

    @Override
    public void enrollStudent(int studentId, int courseId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.ENROLL_STUDENT_TO_COURSE)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setInt(3, 1);
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.executeQuery();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in enrollStudent");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Error in enrollStudent");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in enrollStudent");
        }
    }

    @Override
    public void dropOutStudent(int studentId, int courseId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.DROP_OUT_STUDENT_FROM_COURSE)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeQuery();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in dropOutStudent");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Error in dropOutStudent");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in dropOutStudent");
        }
    }

    @Override
    public boolean isStudentEnrolled(int studentId, int courseId) {
        List<StudentHasCourse> list = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.IS_STUDENT_ENROLLED_TO_COURSE)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            list = parseResultSet(ps.executeQuery());
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in isStudentEnrolled");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Error in isStudentEnrolled");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in isStudentEnrolled");
        }
        return !list.isEmpty();
    }


}
