package dao;

import com.example.faculty.dao.model.CourseDao;
import com.example.faculty.dao.model.impl.CourseDaoImpl;
import com.example.faculty.model.entity.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class CourseDaoTest {

    Course course;

    CourseDao courseDao;

    @Before
    public void setUpt() {
        this.courseDao = new CourseDaoImpl();
        course = new Course();
        course.setId(1);
        course.setTopicId(1);
        course.setCapacity(30);
        course.setSemesterStart(2);
        course.setSemesterDuration(1);
        course.setDescription("This subject shows how computer works");
        course.setTeacherId(1);
        course.setName("Computer design");
    }

    @Test
    public void getAllCourse_Should_Return_True() {
        Course c = courseDao.findById(1);
        Assert.assertNotNull(c);
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
