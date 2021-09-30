package com.example.faculty.controller.command.impl.admin;

import com.example.faculty.controller.command.CommandFactory;
import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import com.example.faculty.model.entity.Course;
import com.example.faculty.model.entity.Teacher;
import com.example.faculty.model.entity.User;

import java.util.List;

import static com.example.faculty.controller.constant.Methods.getRole;

public class EditTeacherCommand extends CommandFactory {

    UserDao userDao = new UserDaoImpl();
    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public String doGet() {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("teacher", userDao.findById(id));

        request.setAttribute("teacherCourses", courseDao.findAllTeachersCourses(id));
        request.setAttribute("freeCourses", courseDao.findFreeCourses());

        return "WEB-INF/jsp/users/admin/editTeacher.jsp";
    }

    @Override
    public String doPost() {


        int teacherId = Integer.parseInt(request.getParameter("id"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String action = request.getParameter("action");
        request.setAttribute("role", getRole(request));

        if (action.equals("delete")) {
            deleteSubject(courseId);
        } else {
            addSubject(courseId, teacherId);
        }
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
