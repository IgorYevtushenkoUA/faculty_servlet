package dao;

import com.example.faculty.dao.model.StudentHasCourseDao;
import com.example.faculty.dao.model.impl.StudentHasCourseDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentHasCourseDaoTest {

    StudentHasCourseDao shcDao ;

    @Before
    public void SetUpt(){
        shcDao = new StudentHasCourseDaoImpl();
    }

    @Test
    public void isStudentEnrolledToCourse(){
        Assert.assertFalse(shcDao.isStudentEnrolled(-1,-1));
    }
}
