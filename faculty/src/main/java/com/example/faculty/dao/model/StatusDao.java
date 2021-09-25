package com.example.faculty.dao.model;

import com.example.faculty.model.entity.Status;

public interface StatusDao {

    Status findById(int id);

    Status findByName(String name);

}
