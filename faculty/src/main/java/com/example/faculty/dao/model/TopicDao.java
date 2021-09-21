package com.example.faculty.dao.model;

import com.example.faculty.model.entity.Topic;

import java.util.List;

public interface TopicDao {

    Topic findByName(String name);

    List<Topic> findAll();
}
