package com.example.faculty.dao.model.impl;

import com.example.faculty.controller.command.impl.LoginCommand;
import com.example.faculty.dao.model.TopicDao;
import com.example.faculty.db.ConnectionPool;
import com.example.faculty.db.Queries;
import com.example.faculty.model.entity.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TopicDaoImpl implements TopicDao {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    private List<Topic> parseResultSet(ResultSet rs) {
        List<Topic> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setId(rs.getInt("id"));
                topic.setName(rs.getString("name"));
                list.add(topic);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error in parseResultSet");
        }
        return list;
    }

    @Override
    public Topic findByName(String name) {
        Topic topic = new Topic();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_TOPIC_BY_NAME)) {
            ps.setString(1, name);
            topic = parseResultSet(ps.executeQuery()).iterator().next();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findByName");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findByName");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findByName");
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
            logger.log(Level.WARNING, "SQLException in findAll");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findAll");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findAll");
        }
        return list;
    }

    @Override
    public Topic findById(int id) {
        Topic topic = new Topic();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_TOPIC_BY_ID)) {
            ps.setInt(1, id);
            topic = parseResultSet(ps.executeQuery()).iterator().next();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findById");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findById");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findById");
        }
        return topic;
    }
}
