package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.TopicDao;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.dao.model.impl.TopicDaoImpl;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Course;
import com.example.faculty.model.entity.Teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.faculty.controller.constant.Methods.getRole;

public class ListCoursesCommand extends CommandFactory {

    private static final int DEFAULT_ELEMS_ON_PAGE = 2;

    CourseDao courseDao = new CourseDaoImpl();
    TopicDao topicDao = new TopicDaoImpl();
    UserDao userDao = new UserDaoImpl();

    @Override
    public String doGet() {
        HttpSession session = request.getSession();
        String course = getParam(request.getParameter("course"));
        String duration = getParam(request.getParameter("duration"));
        String capacity = getParam(request.getParameter("capacity"));
        String topic = getParam(request.getParameter("topic"));
        String teacher = getParam(request.getParameter("teacher"));
        String courseId = (String) session.getAttribute("courseId");
        String sortType = getParam(request.getParameter("sortType"));
        int page = getParam(request.getParameter("page"), 1);
        sortType = sortType.equals("") ? "ASC" : sortType;


        List<Course> courses = findCourses(courseDao, course, duration, capacity, topic, teacher, sortType, topicDao, userDao);
        int pages = (int) Math.ceil(courses.size() / DEFAULT_ELEMS_ON_PAGE);
        session.setAttribute("pages", pages);

        courses = courses.stream().skip((page - 1) * DEFAULT_ELEMS_ON_PAGE).limit(DEFAULT_ELEMS_ON_PAGE).collect(Collectors.toList());

        setParam(course, duration, capacity, topic, teacher, sortType);
        request.setAttribute("pages", pages);
        request.setAttribute("topics", topicDao.findAll());
        request.setAttribute("teachers", userDao.findAllTeacher());
        request.setAttribute("courses", courses);
        request.setAttribute("sortType", sortType);
        request.setAttribute("classes", setBtnClass(sortType));
        request.setAttribute("role", getRole(request));

        if (courseId == null || courseId.isEmpty())
            return "WEB-INF/jsp/courses.jsp";

        return "WEB-INF/jsp/course.jsp";
    }

    @Override
    public String doPost() {
        return "controller?command=courses";
    }

    private String getParam(String param) {
        if (param == null || param.isEmpty())
            return "";
        return param;
    }

    private int getParam(String param, int defaultValue) {
        if (param == null || param.isEmpty())
            return defaultValue;
        return Integer.parseInt(param);
    }

    private void setParam(String course, String duration, String capacity, String topic, String teacher, String sortType) {
        request.setAttribute("course", course);
        request.setAttribute("duration", duration);
        request.setAttribute("topic", topic);
        request.setAttribute("teacher", teacher);
        request.setAttribute("sortType", sortType);
    }

    private List<Course> findCourses(CourseDao dao, String course, String duration, String capacity, String topic, String teacher, String sortType, TopicDao topicDao, UserDao userDao) {

        List<Course> courses = dao.findAll().stream().sorted(Comparator.comparing(Course::getName, (c1, c2) -> {
            if (sortType.equals("ASC")) return c2.compareTo(c1);
            return c1.compareTo(c2);
        })).collect(Collectors.toList());

        if (course.isEmpty() && duration.isEmpty() && capacity.isEmpty() && topic.isEmpty() && teacher.isEmpty())
            return courses;

        List<Integer> durationList = getDurationList(duration, courses);
        List<Integer> capacityList = getCapacityList(capacity, courses);
        List<Integer> topicList = getTopicList(topic, courses, topicDao);
        List<Integer> teacherList = getTeacherList(teacher, courses, userDao);

        return dao.findCourseByParams(course, durationList, capacityList, topicList, teacherList, sortType);
    }


    private List<Integer> getDurationList(String duration, List<Course> courses) {
        return duration.isEmpty()
                ? courses.stream().map(Course::getSemesterDuration)
                .distinct().collect(Collectors.toList())
                : List.of(Integer.parseInt(duration));
    }

    private List<Integer> getCapacityList(String capacity, List<Course> courses) {
        return capacity.isEmpty()
                ? courses.stream().map(Course::getCapacity)
                .distinct().collect(Collectors.toList())
                : List.of(Integer.parseInt(capacity));
    }

    private List<Integer> getTopicList(String topic, List<Course> courses, TopicDao topicDao) {
        return topic.isEmpty()
                ? courses.stream().map(Course::getTopicId).distinct().collect(Collectors.toList())
                : List.of(topicDao.findByName(topic).getId());
    }


    private List<Integer> getTeacherList(String teacher, List<Course> courses, UserDao userDao) {
        return teacher.isEmpty()
                ? courses.stream().map(Course::getTeacherId)
                .distinct().collect(Collectors.toList())
                : userDao.findAllTeachersByPIB(teacher).stream().map(Teacher::getId)
                .distinct().collect(Collectors.toList());
    }

    private List<String> setBtnClass(String sortType) {
        if (sortType.equals("ASC")) return List.of("btn btn-primary", "btn btn-outline-danger");
        return List.of("btn btn-outline-primary", "btn btn-danger");
    }

}
