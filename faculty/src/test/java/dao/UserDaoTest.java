package dao;

import com.example.faculty.dao.model.UserDao;
import com.example.faculty.dao.model.impl.UserDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {

    UserDao userDao;

    @Before
    public void SetUpt(){
        userDao = new UserDaoImpl();
    }

    @Test
    public void findById(){
        Assert.assertNotNull(userDao.findById(1));
    }

    @Test
    public void findAllTeachers(){
        Assert.assertFalse(userDao.findAllTeacher().isEmpty());
    }

    @Test
    public void findAllStudents(){
        Assert.assertFalse(userDao.findAllStudent().isEmpty());
    }

}
