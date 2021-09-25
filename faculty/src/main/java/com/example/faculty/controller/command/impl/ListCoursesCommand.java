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
import com.example.faculty.model.entity.Topic;
import com.example.faculty.model.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

public class ListCoursesCommand extends CommandFactory {
    @Override
    public String doGet() {
        HttpSession session = request.getSession();
        String course = getParam(request.getParameter("course"));
        String duration = getParam(request.getParameter("duration"));
        String capacity = getParam(request.getParameter("capacity"));
        String topic = getParam(request.getParameter("topic"));
        String teacher = getParam(request.getParameter("teacher"));
        String filter = (String) session.getAttribute("filter");

        CourseDao courseDao = new CourseDaoImpl();
        TopicDao topicDao = new TopicDaoImpl();
        UserDao userDao = new UserDaoImpl();

        request.setAttribute("topics", topicDao.findAll());
        request.setAttribute("teachers", userDao.findAllTeacher());
        request.setAttribute("courses", findCourses(courseDao, course, duration, capacity, topic, teacher, topicDao, userDao));


        if (course.isEmpty() && duration.isEmpty() && capacity.isEmpty() && topic.isEmpty() && teacher.isEmpty()) {
            session.setAttribute("filter", "1");
            return "jsp/courses.jsp";
        }
        if (filter == null || filter.equals("0")) {
            session.setAttribute("filter", "1");
            return "jsp/courses.jsp";
        }

        session.setAttribute("filter", "0");
        return "controller?command=courses";
    }

    private String getParam(String param) {
        if (param == null || param.isEmpty())
            return "";
        return param;
    }

    @Override
    public String doPost() {
        System.out.println("do post man");
        return "controller?command=courses";
    }


    private List<Course> findCourses(CourseDao dao, String course, String duration, String capacity, String topic, String teacher, TopicDao topicDao, UserDao userDao) {

        List<Course> courses = dao.findAll();

        if (course.isEmpty() && duration.isEmpty() && capacity.isEmpty() && topic.isEmpty() && teacher.isEmpty())
            return courses;


        List<Integer> durationList = getDurationList(duration, courses);
        List<Integer> capacityList = getCapacityList(capacity, courses);
        List<Integer> topicList = getTopicList(topic, courses, topicDao);
        List<Integer> teacherList = getTeacherList(teacher, courses, userDao);

        return dao.findCourseByParams(course, durationList, capacityList, topicList, teacherList);
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


}
