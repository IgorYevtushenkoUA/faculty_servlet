package com.example.faculty.db;

public class Queries {

    // COURSE
    public static final String SELECT_ALL_COURSES = ""; // findAll()
    public static final String SELECT_COURSE_BY_ID = ""; // findById
    public static final String INSERT_COURSE = ""; // save()
    public static final String UPDATE_COURSE = ""; // update()
    public static final String SELECT_COURSES_BY_PARAMS = ""; // findAllByParams(List<String> courseName, List<Integer> duration,List<Integer> capacity, List<String> topic,    List<Integer> teacherId, Pageable pageable)
    public static final String SELECT_ALL_TEACHERS_COURSES = "";// findAllTeacherCourses
    public static final String DELETE_COURSE_BY_ID = ""; // deleteById()
    public static final String INSERT_TEACHER_TO_COURSE = ""; // addTeacherToCourse()
    public static final String DELETE_TEACHER_FROM_COURSE = ""; // deleteTeacherFromCourse()
    public static final String SELECT_ALL_STUDENT_COURSES_BY_TYPE = ""; // findAllStudentCoursesByType
    // ROLE
    public static final String SELECT_ROLE_BY_NAME = "";
    // STATUS
    public static final String SELECT_STATUS_BY_NAME = "";
    // STUDENT_HAS_COURSE
    public static final String ENROLL_STUDENT_TO_COURSE = "";
    public static final String SELECT_ALL_STUDENTS_BY_COURSE_AND_YEAR_AND_NAME = "";    // TOPIC
    public static final String INSERT_STUDENT_HAS_COURSE = "";
    public static final String SELECT_ALL_BY_STUDENT_AND_COURSE = "";
    // TOPIC
    public static final String SELECT_TOPIC_BY_NAME = "select * from topic t where t.name=?";
    public static final String SELECT_ALL_TOPICS = "select * from topic";
    // USER
    public static final String SELECT_ALL_USERS = "select * from users";
    public static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "select * from users u where u.email=? and u.password=?";
    public static final String SELECT_USER_BY_EMAIL = "select * from user u where u.email=?";
    public static final String SELECT_USER_BY_ID = "select * from users u where u.id = ?";
    public static final String INSERT_USER = "insert into user (first_name, second_name, last_name, email, password, role_id) values (?,?,?,?,?,?)";
    public static final String SELECT_TEACHER_BY_PIB = "select * from user u where lower(concat(u.last_name,' ',u.first_name,' ',u.last_name)) like lower(concat('%',?,'%')) and u.role_id=2";
    public static final String SELECT_TEACHER_BY_ID = "select * from user u where u.id=?";
    public static final String SELECT_ALL_TEACHERS = "select * from user u where u.role_id=2";
    public static final String SELECT_ALL_STUDENTS = "select * from user u where u.role_id=3";
    public static final String SELECT_STUDENT_INFO_BY_ID_AND_COURSE_ID = "";// ТУТ ТРОЗИНЕ ЗРОЗУМІЛО ЧОМУ Я ПИСАВ ЗА КУРСОМ, ЯКЩО БЕЗ КУРСУ МОЖНА
    public static final String SELECT_STUDENT_BY_ID = "select * from user u where u.id=?";
    public static final String SELECT_STUDENT_BY_PIB = "select * from user u where lower(concat(u.last_name,' ',u.first_name,' ',u.last_name)) like lower(concat('%',?,'%')) and u.role_id=3";
    public static final String SELECT_ENROLLED_STUDENT_TO_COURSE = "select u from user u inner join student_has_course shc on shc.user_id=u.id and shc.course_id=?";
    public static final String UPDATE_USER="";

}
