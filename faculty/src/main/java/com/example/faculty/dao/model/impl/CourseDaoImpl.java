package com.example.faculty.dao.model.impl;

import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.db.ConnectionPool;
import com.example.faculty.db.Queries;
import com.example.faculty.dto.StudentCourseInfoDto;
import com.example.faculty.model.entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class CourseDaoImpl implements CourseDao {

    int i = 1;

    private void prepareStatementForCreateCourse(PreparedStatement ps, Course course) {
        i = 1;
        try {
            ps.setInt(i++, course.getTopicId());
            ps.setInt(i++, course.getCapacity());
            ps.setInt(i++, course.getSemesterStart());
            ps.setInt(i++, course.getSemesterDuration());
            ps.setString(i++, course.getDescription());
            ps.setInt(i++, course.getTeacherId());
            ps.setString(i++, course.getName());
        } catch (SQLException e) {
        }
    }

    private void prepareStatementForUpdateCourse(PreparedStatement ps, Course course) {
        i = 1;
        System.out.println("here before");
        try {
            ps.setInt(i++, course.getTopicId());
            ps.setInt(i++, course.getCapacity());
            ps.setInt(i++, course.getSemesterStart());
            ps.setInt(i++, course.getSemesterDuration());
            ps.setString(i++, course.getDescription());
            ps.setInt(i++, course.getTeacherId());
            ps.setString(i++, course.getName());
            ps.setInt(i++, course.getId());
            System.out.println("here after");
        } catch (SQLException e) {

        }
    }

    private List<Course> parseResultSet(ResultSet rs) {
        List<Course> courses = new ArrayList<>();
        try {
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setTopicId(rs.getInt("topic_id"));
                course.setCapacity(rs.getInt("capacity"));
                course.setSemesterStart(rs.getInt("semester_start"));
                course.setSemesterDuration(rs.getInt("semester_duration"));
                course.setDescription(rs.getString("description"));
                course.setTeacherId(rs.getInt("teacher_id"));
                course.setName(rs.getString("name"));
                courses.add(course);
            }
        } catch (SQLException e) {
        }
        return courses;
    }

    private List<StudentCourseInfoDto> parseStudentCourseInfoDtoResultSet(ResultSet rs) {
        List<StudentCourseInfoDto> list = new ArrayList<>();
        try {
            while (rs.next()) {
                StudentCourseInfoDto studentCourseInfoDto = new StudentCourseInfoDto();
                studentCourseInfoDto.setCourseId(rs.getInt("id"));
                studentCourseInfoDto.setCourseName(rs.getString("name"));
                studentCourseInfoDto.setMark(rs.getInt("mark"));
                list.add(studentCourseInfoDto);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    private List<Integer> parseResultSetForList(ResultSet rs, String columnLabel) {
        List<Integer> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(rs.getInt(columnLabel));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_ALL_COURSES)) {
            courses = parseResultSet(ps.executeQuery());
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return courses;
    }

    @Override
    public Course findById(int id) {
        Course course = new Course();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_COURSE_BY_ID)) {
            ps.setInt(1, id);
            course = parseResultSet(ps.executeQuery()).iterator().next();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return course;
    }

    @Override
    public List<Course> findByName(String name) {
        List<Course> list = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_COURSE_BY_NAME)) {
            ps.setString(1, name);
            list = parseResultSet(ps.executeQuery());
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return list;
    }


    @Override
    public Course addCourse(Course course) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.INSERT_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForCreateCourse(ps, course);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                course.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return course;
    }

    @Override
    public Course updateCourse(Course course) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.UPDATE_COURSE)) {
            prepareStatementForUpdateCourse(ps, course);
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return course;
    }

    @Override
    public List<Course> findCourseByParams(String courseName,
                                           List<Integer> duration,
                                           List<Integer> capacity,
                                           List<Integer> topic,
                                           List<Integer> teacher,
                                           String sortType) {
        List<Course> courses = new ArrayList<>();
        String sql = buildSQL(courseName, duration, capacity, topic, teacher, sortType);
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            courses = parseResultSet(ps.executeQuery());
            System.out.println("after ps.execute");
            System.out.println(courses);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return courses;
    }

    private String buildSQL(String courseName,
                            List<Integer> duration,
                            List<Integer> capacity,
                            List<Integer> topic,
                            List<Integer> teacher,
                            String sortType) {
        StringBuilder sql = new StringBuilder();
        sql.append("select *\n");
        sql.append("from course c\n");
        sql.append("where lower(c.name) like lower(concat('%','" + courseName + "','%'))\n");
        sql.append(duration.isEmpty() ? ("  and c.semester_duration in (0)\n") : ("  and c.semester_duration in (" + String.join(", ", duration.stream().map(Object::toString).collect(Collectors.toList())) + ")\n"));
        sql.append(capacity.isEmpty() ? ("  and c.capacity in (0)\n") : ("  and c.capacity in (" + String.join(", ", capacity.stream().map(Object::toString).collect(Collectors.toList())) + ")\n"));
        sql.append(topic.isEmpty() ? ("  and c.topic_id in (0)\n") : ("  and c.topic_id in (" + String.join(", ", topic.stream().map(Object::toString).collect(Collectors.toList())) + ")\n"));
        sql.append(teacher.isEmpty() ? ("  and c.teacher_id in (0)") : ("  and c.teacher_id in (" + String.join(", ", teacher.stream().map(Object::toString).collect(Collectors.toList())) + ")\n"));
        sql.append("order by c.name " + sortType);
        System.out.println(sql);
        return sql.toString();
    }

    @Override
    public List<Course> findAllTeachersCourses(int teacherId) {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_ALL_TEACHERS_COURSES)) {
            ps.setInt(1, teacherId);
            courses = parseResultSet(ps.executeQuery());
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return courses;
    }

    @Override
    public boolean deleteCourseById(int id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.DELETE_COURSE_BY_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public Course deleteTeacherFromCourse(int courseId) {
        Course course = new Course();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.DELETE_TEACHER_FROM_COURSE)) {

            ps.setInt(1, courseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return course;
    }


    @Override
    public List<StudentCourseInfoDto> findAllStudentCoursesByType(int studentId, String type) {
        List<StudentCourseInfoDto> courses = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_ALL_STUDENT_COURSES_BY_TYPE)) {
            ps.setInt(1, studentId);
            ps.setString(2, type);
            courses = parseStudentCourseInfoDtoResultSet(ps.executeQuery());
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return courses;
    }

    @Override
    public List<Course> findFreeCourses() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_FREE_COURSES)) {
            courses = parseResultSet(ps.executeQuery());
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return courses;
    }

}
