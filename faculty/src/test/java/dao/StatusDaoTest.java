package dao;

import com.example.faculty.dao.model.StatusDao;
import com.example.faculty.dao.model.impl.StatusDaoImpl;
import com.example.faculty.model.enums.STATUS;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StatusDaoTest {

    StatusDao statusDao;

    @Before
    public void SetUpt() {
        statusDao = new StatusDaoImpl();
    }

    @Test
    public void findById_Should_Return_Not_Null(){
        Assert.assertNotNull(statusDao.findById(1));
    }

    @Test
    public void findByName_Should_Return_True(){
        Assert.assertTrue(STATUS.NOT_STARTED.name().equals(statusDao.findByName("NOT_STARTED").getName()));
    }

    @Test
    public void findByName_Should_Return_False(){
        Assert.assertFalse(STATUS.FINISHED.name().equals(statusDao.findByName("NOT_STARTED").getName()));
    }

}
