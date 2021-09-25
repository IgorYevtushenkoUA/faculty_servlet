package com.example.faculty.dao.model;

import com.example.faculty.model.entity.Role;

public interface RoleDao {

    Role findById(int id);

    Role findByName(String name);
}
