package com.example.faculty.db;

public class Queries {

    // COURSE
    public static final String T = "";
    public static final String SELECT_ALL_COURSES = "select * from course"; // findAll()
    public static final String SELECT_COURSE_BY_ID = "select * from course c where c.id = ?"; // findById
    public static final String SELECT_COURSE_BY_NAME = "select * from course c where lower(c.name) like lower(concat('%',?,'%'))"; // findById
    public static final String INSERT_COURSE = "insert into course(topic_id, capacity, semester_start, semester_duration, description, teacher_id, name)\n" +
            "values (?, ?, ?, ?, ?, ?, ?)"; // save()
    public static final String UPDATE_COURSE = "update course \n" +
            "set topic_id          = ?,\n" +
            "    capacity          = ?,\n" +
            "    semester_start    = ?,\n" +
            "    semester_duration = ?,\n" +
            "    description       = ?,\n" +
            "    teacher_id        = ?,\n" +
            "    name              = ?"; // update()
    public static final String SELECT_COURSES_BY_PARAMS = "select * \n" +
            "from course c \n" +
            "where lower(c.name) like lower(concat('%',?,'%')) and \n" +
            "      c.semester_duration in (?) and\n" +
            "      c.capacity in (?) and \n" +
            "      c.topic_id in (?) and\n" +
            "      c.teacher_id in (?)"; // findAllByParams(List<String> courseName, List<Integer> duration,List<Integer> capacity, List<Integer> topic,    List<Integer> teacherId, Pageable pageable)
    public static final String SELECT_ALL_TEACHERS_COURSES = "select c\n" +
            "from course c\n" +
            "where c.teacher_id = ?";// findAllTeacherCourses
    public static final String DELETE_COURSE_BY_ID = "delete\n" +
            "from course c\n" +
            "where c.id = ?"; // deleteById()
    public static final String INSERT_TEACHER_TO_COURSE = "update course \n" +
            "set teacher_id = ?\n" +
            "where id = ?"; // addTeacherToCourse()
    public static final String DELETE_TEACHER_FROM_COURSE = "update course\n" +
            "set teacher_id = null\n" +
            "where id = ?"; // deleteTeacherFromCourse()
    public static final String SELECT_ALL_STUDENT_COURSES_BY_TYPE = "select c\n" +
            "from course c\n" +
            "         inner join student_has_course shc on shc.course_id = c.id\n" +
            "where shc.student_id = ?\n" +
            "  and shc.status_id in (select s.id from status s where s.name = ?)"; // findAllStudentCoursesByType


    public static final String SELECT_ALL_COURSES_NAME_STRING="select distinct(c.name) from course c";
    public static final String SELECT_COURSES_NAME_STRING="select c.name from course c where lower(c.name) like (concat('%',?,'%'))";

    public static final String SELECT_ALL_DURATIONS_INT="select distinct(c.semester_duration) from course c";
    public static final String SELECT_DURATION_INT="select c.semester_duration from course c where c.semester_duration = ?";

    public static final String SELECT_ALL_CAPACITIES_INT="select distinct(c.capacity) from course c";
    public static final String SELECT_CAPACITY_INT="select c.capacity from course c where c.capacity=?";

    public static final String SELECT_ALL_TOPICS_INT="select c.topic_id from course c";
    public static final String SELECT_TOPIC_INT="select c.topic_id from course c where c.topic_id = ?";

    public static final String SELECT_ALL_TEACHERS_ID="select c.teacher_id from course c";
    public static final String SELECT_TEACHERS_NAME_ID="select distinct (c.teacher_id)\n" +
            "from course c\n" +
            "         inner join users t on t.id = c.teacher_id\n" +
            "where (concat(lower(t.last_name),' ', lower(t.first_name),' ', lower(t.last_name)) like ('%me%'))";

    // ROLE
    public static final String SELECT_ROLE_BY_ID = "select * from role r where r.id=?";
    public static final String SELECT_ROLE_BY_NAME = "select * from role r where r.name=?";
    // STATUS
    public static final String SELECT_STATUS_BY_NAME = "select * from status s where s.name = ?";
    public static final String SELECT_STATUS_BY_ID = "select * from status s where s.id = ?";

    // STUDENT_HAS_COURSE
    public static final String ENROLL_STUDENT_TO_COURSE = "insert into student_has_course(student_id, course_id, status_id, mark, recording_time)\n" +
            "values (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_STUDENTS_BY_COURSE_AND_YEAR_AND_NAME = "";
    public static final String INSERT_STUDENT_HAS_COURSE = "";
    public static final String SELECT_ALL_BY_STUDENT_AND_COURSE = "";
    // TOPIC
    public static final String SELECT_TOPIC_BY_ID = "select * from topic t where t.id=?";
    public static final String SELECT_TOPIC_BY_NAME = "select * from topic t where t.name=?";
    public static final String SELECT_ALL_TOPICS = "select * from topic";
    // USER
    public static final String SELECT_ALL_USERS = "select * from users";
    public static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "select * from users u where u.email=? and u.password=?";
    public static final String SELECT_USER_BY_EMAIL = "select * from users u where u.email=?";
    public static final String SELECT_USER_BY_ID = "select * from users u where u.id = ?";
    public static final String INSERT_USER = "insert into users (first_name, second_name, last_name, email, password, role_id) values (?,?,?,?,?,?)";
    public static final String INSERT_TEACHER = "insert into teacher (id) values (?)";
    public static final String INSERT_STUDENT = "insert into student (id, course_num) values (?,?)";
    public static final String SELECT_TEACHER_BY_PIB = "select * from users u where lower(concat(u.last_name,' ',u.first_name,' ',u.last_name)) like lower(concat('%',?,'%')) and u.role_id=2";
    public static final String SELECT_TEACHER_BY_ID = "select * from users u where u.id=?";
    public static final String SELECT_ALL_TEACHERS = "select * from users u where u.role_id=2";
    public static final String SELECT_ALL_STUDENTS = "select u.id, u.first_name, u.second_name, u.last_name, u.email, u.password, u.role_id, s.course_num, s.enable\n" +
            "from users u\n" +
            "         inner join student s on u.id = s.id";
    public static final String SELECT_STUDENT_INFO_BY_ID_AND_COURSE_ID = "";// ТУТ ТРОЗИНЕ ЗРОЗУМІЛО ЧОМУ Я ПИСАВ ЗА КУРСОМ, ЯКЩО БЕЗ КУРСУ МОЖНА
    public static final String SELECT_STUDENT_BY_ID = "select u.id, u.first_name, u.second_name, u.last_name, u.email, u.password, u.role_id, s.course_num, s.enable\n" +
            "from users u\n" +
            "         inner join student s on u.id = s.id and u.id = ?";
    public static final String SELECT_STUDENT_BY_PIB = "select u.id,\n" +
            "       u.first_name,\n" +
            "       u.second_name,\n" +
            "       u.last_name,\n" +
            "       u.email,\n" +
            "       u.password,\n" +
            "       u.role_id,\n" +
            "       s.course_num,\n" +
            "       s.enable\n" +
            "from users u\n" +
            "         inner join student s\n" +
            "                    on u.id = s.id\n" +
            "where lower(concat(u.last_name\n" +
            "    , ' '\n" +
            "    , u.first_name\n" +
            "    , ' '\n" +
            "    , u.last_name)) like lower(concat('%'\n" +
            "    , ? \n" +
            "    , '%'))\n";
    public static final String SELECT_ENROLLED_STUDENT_TO_COURSE = "select u.id,\n" +
            "       u.first_name,\n" +
            "       u.second_name,\n" +
            "       u.last_name,\n" +
            "       s.course_num,\n" +
            "       shc.mark,\n" +
            "       shc.recording_time\n" +
            "from users u\n" +
            "         inner join student s\n" +
            "                    on u.id = s.id\n" +
            "inner join student_has_course shc on s.id = shc.student_id\n" +
            "where shc.course_id=?";
    public static final String UPDATE_USER="";

}
