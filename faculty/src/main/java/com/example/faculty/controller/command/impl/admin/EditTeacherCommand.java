package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Course;
import com.example.faculty.model.entity.Teacher;
import com.example.faculty.model.entity.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.faculty.controller.constant.Methods.getRole;

public class EditTeacherCommand extends CommandFactory {

    UserDao userDao = new UserDaoImpl();
    CourseDao courseDao = new CourseDaoImpl();
    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String doGet() {
        logger.log(Level.INFO, "Enter EditTeacherCommand doGet()");
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("teacher", userDao.findById(id));

        request.setAttribute("teacherCourses", courseDao.findAllTeachersCourses(id));
        request.setAttribute("freeCourses", courseDao.findFreeCourses());

        logger.log(Level.INFO, "Leave EditTeacherCommand doGet()");

        return "WEB-INF/jsp/users/admin/editTeacher.jsp";
    }

    @Override
    public String doPost() {

        logger.log(Level.INFO, "Enter EditTeacherCommand doPost()");

        int teacherId = Integer.parseInt(request.getParameter("id"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String action = request.getParameter("action");
        request.setAttribute("role", getRole(request));

        if (action.equals("delete")) {
            logger.log(Level.INFO, "Delete subject in teacher");
            deleteSubject(courseId);
        } else {
            logger.log(Level.INFO, "Add new subject to teacher");
            addSubject(courseId, teacherId);
        }

        logger.log(Level.INFO, "Leave EditTeacherCommand doPost()");

        return "controller?command=teacher-edit&id=" + teacherId;
    }

    private void deleteSubject(int courseId) {
        courseDao.deleteTeacherFromCourse(courseId);
    }

    private void addSubject(int courseId, int teacherId) {
        Course course = courseDao.findById(courseId);
        course.setTeacherId(teacherId);
        courseDao.updateCourse(course);
    }
}
