package com.example.faculty.dao.model.impl;

import com.example.faculty.dao.model.TopicDao;
import com.example.faculty.db.ConnectionPool;
import com.example.faculty.db.Queries;
import com.example.faculty.model.entity.Status;
import com.example.faculty.model.entity.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TopicDaoImpl implements TopicDao {

    int i = 1;

    private void prepareStatementForCreateTopic(PreparedStatement ps, Topic topic) {
        i = 1;
        try {
            ps.setString(i++, topic.getName());
        } catch (SQLException e) {
        }
    }

    private List<Topic> parseResultSet(ResultSet rs) {
        i = 1;
        List<Topic> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setId(rs.getInt("id"));
                topic.setName(rs.getString("name"));
                list.add(topic);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    @Override
    public Topic findByName(String name) {
        Topic topic = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_TOPIC_BY_NAME)) {
            ps.setString(1, name);
            topic = parseResultSet(ps.executeQuery()).iterator().next();
        } catch (SQLException e) {
            System.out.println("SQLException e");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException e");
        }
        return topic;
    }

    @Override
    public List<Topic> findAll() {
        List<Topic> list = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_ALL_TOPICS)) {
            list = parseResultSet(ps.executeQuery());
        } catch (SQLException e) {
            System.out.println("SQLException e");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException e");
        }
        return list;
    }

    @Override
    public Topic findById(int id) {
        Topic topic = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_TOPIC_BY_ID)) {
            ps.setInt(1, id);
            topic = parseResultSet(ps.executeQuery()).iterator().next();
        } catch (SQLException e) {
            System.out.println("SQLException e");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException e");
        }
        return topic;
    }
}
