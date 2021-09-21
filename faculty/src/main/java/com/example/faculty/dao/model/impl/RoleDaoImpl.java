package com.example.faculty.dao.model.impl;

import com.example.faculty.dao.model.RoleDao;
import com.example.faculty.db.ConnectionPool;
import com.example.faculty.db.Queries;
import com.example.faculty.model.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RoleDaoImpl implements RoleDao {

    public List<Role> parseResultSet(ResultSet rs) {
        List<Role> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                list.add(role);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    @Override
    public Role findById(int id) {
        Role role = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_ROLE_BY_ID)) {
            stmt.setInt(1, id);
            role = parseResultSet(stmt.executeQuery()).iterator().next();
        } catch (SQLException e) {
            System.out.println("SQLException e");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException e");
        }
        return role;
    }

}
