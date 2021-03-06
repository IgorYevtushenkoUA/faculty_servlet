package com.example.faculty.dao.model.impl;

import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.db.ConnectionPool;
import com.example.faculty.db.Queries;
import com.example.faculty.dto.StudentCourseInfoDto;
import com.example.faculty.model.entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CourseDaoImpl implements CourseDao {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

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
            logger.log(Level.WARNING, "error in prepareStatementForCreateCourse");
        }
    }

    private void prepareStatementForUpdateCourse(PreparedStatement ps, Course course) {
        i = 1;
        try {
            ps.setInt(i++, course.getTopicId());
            ps.setInt(i++, course.getCapacity());
            ps.setInt(i++, course.getSemesterStart());
            ps.setInt(i++, course.getSemesterDuration());
            ps.setString(i++, course.getDescription());
            ps.setInt(i++, course.getTeacherId());
            ps.setString(i++, course.getName());
            ps.setInt(i++, course.getId());
        } catch (SQLException e) {
            logger.log(Level.WARNING, "error in prepareStatementForUpdateCourse");

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
            logger.log(Level.WARNING, "error in parseResultSet");
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
            logger.log(Level.WARNING, "error in parseStudentCourseInfoDtoResultSet");
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
            logger.log(Level.WARNING, "SQLException in findAll");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findAll");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findAll");
        }
        return courses;
    }

    @Override
    public Course findById(int id) {
        Course course = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_COURSE_BY_ID)) {
            ps.setInt(1, id);
            course = parseResultSet(ps.executeQuery()).iterator().next();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findById");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findById");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findById");
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
            logger.log(Level.WARNING, "SQLException in findByName");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findByName");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findByName");
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
            logger.log(Level.WARNING, "SQLException in addCourse");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in addCourse");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in addCourse");
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
            logger.log(Level.WARNING, "SQLException in updateCourse");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in updateCourse");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in updateCourse");
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
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findCourseByParams");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findCourseByParams");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findCourseByParams");
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
            logger.log(Level.WARNING, "SQLException in findAllTeachersCourses");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findAllTeachersCourses");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findAllTeachersCourses");
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
            logger.log(Level.WARNING, "SQLException in deleteCourseById");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in deleteCourseById");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in deleteCourseById");
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
            logger.log(Level.WARNING, "SQLException in deleteTeacherFromCourse");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in deleteTeacherFromCourse");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in deleteTeacherFromCourse");
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
            logger.log(Level.WARNING, "SQLException in findAllStudentCoursesByType");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findAllStudentCoursesByType");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findAllStudentCoursesByType");
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
            logger.log(Level.WARNING, "SQLException in findFreeCourses");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findFreeCourses");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findFreeCourses");
        }
        return courses;
    }

}
