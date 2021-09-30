package dao;

import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.model.entity.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoTest {
    private CourseDao courseDao;

    @Before
    public void setUpt() {
        this.courseDao = new CourseDaoImpl();
    }

    @Test
    public void getAllCourse_Should_Return_True() {
        List<Course> courses = courseDao.findAll();
        Assert.assertTrue(!courses.isEmpty());
    }

    @Test
    public void getCourseByNotExistedKey_Should_Return_Null() {
        Assert.assertNull(courseDao.findById(-1));
    }

    @Test
    public void getCourseByExistedKed_Should_Return_Not_Null() {
        Assert.assertNotNull(courseDao.findById(1));
    }

    @Test
    public void findAllTeachersCoursesWithNotExistedTeacherKey() {
        Assert.assertArrayEquals(new ArrayList().toArray(), courseDao.findAllTeachersCourses(-1).toArray());
    }

    @Test
    public void findAllTeachersCoursesWithExistedTeacherKey() {
        Assert.assertNotEquals(new ArrayList().toArray(), courseDao.findAllTeachersCourses(2).toArray());
    }

}
