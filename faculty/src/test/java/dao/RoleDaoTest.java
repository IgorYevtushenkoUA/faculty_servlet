package dao;

import com.example.faculty.dao.model.RoleDao;
import com.example.faculty.dao.model.impl.RoleDaoImpl;
import com.example.faculty.model.enums.ROLE;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoleDaoTest {

    RoleDao roleDao;

    @Before
    public void SetUpt() {
        roleDao = new RoleDaoImpl();
    }

    @Test
    public void findById_Should_Return_True(){
        Assert.assertNotNull(roleDao.findById(1));
    }

    @Test
    public void findByName_Should_Return_True(){
        Assert.assertTrue(ROLE.ROLE_STUDENT.name().equals(roleDao.findByName("ROLE_STUDENT").getName()));
    }

    @Test
    public void findByName_Should_Return_FALSE(){
        Assert.assertFalse(ROLE.ROLE_TEACHER.name().equals(roleDao.findByName("ROLE_STUDENT").getName()));
    }

}
