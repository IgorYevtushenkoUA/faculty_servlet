package com.example.faculty.dao.model.impl;

import com.example.faculty.dao.model.StudentHasCourseDao;
import com.example.faculty.db.ConnectionPool;
import com.example.faculty.db.Queries;
import com.example.faculty.model.entity.StudentHasCourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StudentHasCourseDaoImpl implements StudentHasCourseDao {

    int i = 1;

    private void prepareStatementForCreate(PreparedStatement ps, StudentHasCourse shc) {
        i = 1;
        try {
            ps.setInt(i++, shc.getStudentId());
            ps.setInt(i++, shc.getCourseId());
            ps.setInt(i++, shc.getStatusId());
            ps.setInt(i++, shc.getMark());
            ps.setTimestamp(i++, shc.getRecordingTime());
        } catch (SQLException e) {
        }
    }

    private void prepareStatementForUpdate(PreparedStatement ps, StudentHasCourse shc) {
        i = 1;
        try {
            ps.setInt(i++, shc.getMark());
        } catch (SQLException e) {
        }
    }

    private List<StudentHasCourse> parseResultSet(ResultSet rs) {
        i = 1;
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
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
    }

    // todo
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
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
    }

    // todo
    @Override
    public void dropOutStudent(int studentId, int courseId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.DROP_OUT_STUDENT_FROM_COURSE)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
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
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return !list.isEmpty();
    }


}
