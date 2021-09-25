package com.example.faculty.controller.command.impl;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.constant.PageConstants;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.TopicDao;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.dao.model.impl.TopicDaoImpl;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Course;

import javax.servlet.http.HttpSession;
import java.util.List;

public class ListCoursesCommand extends CommandFactory {
    @Override
    public String doGet() {
        HttpSession session = request.getSession();
        String course = request.getParameter("course");
        String duration = request.getParameter("duration");
        String capacity = request.getParameter("capacity");
        String topic = request.getParameter("topic");
        String teacher = request.getParameter("teacher");

        CourseDao courseDao = new CourseDaoImpl();
        TopicDao topicDao = new TopicDaoImpl();
        UserDao userDao = new UserDaoImpl();

        request.setAttribute("courses", findCourses(courseDao, course, duration, capacity, topic, teacher));
        request.setAttribute("topics", topicDao.findAll());
        request.setAttribute("topics", userDao.findAllTeacher());

        return PageConstants.COURSES;
    }

    @Override
    public String doPost() {
        return "controller?command=courses";
    }


    private List<Course> findCourses(CourseDao dao, String course, String duration, String capacity, String topic, String teacher) {
        if (course.isEmpty() && duration.isEmpty() && capacity.isEmpty() && topic.isEmpty() && teacher.isEmpty())
            return dao.findAll();
//        List<Integer> durationList = dao.setDurationList(Integer.valueOf(duration));
//        List<Integer> capacityList = dao.setCapacityList(Integer.valueOf(capacity));
//        List<Integer> topicList = dao.setTopicList(Integer.valueOf(topic));
//        List<Integer> teacherList = dao.setTeacherList(Integer.valueOf(teacher));
//        return dao.findCourseByParams(course, durationList, capacityList, topicList, teacherList);
        return null;
    }


}
