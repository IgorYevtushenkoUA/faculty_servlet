package dao;

import com.example.faculty.dao.model.TopicDao;
import com.example.faculty.dao.model.impl.TopicDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TopicDaoTest {

    TopicDao topicDao;

    @Before
    public void SetUpt(){
        topicDao = new TopicDaoImpl();
    }

    @Test
    public void findById(){
        Assert.assertNotNull(topicDao.findById(1));
    }

    @Test
    public void findByName(){
        Assert.assertNotNull(topicDao.findByName("INFORMATICS"));
    }


}
