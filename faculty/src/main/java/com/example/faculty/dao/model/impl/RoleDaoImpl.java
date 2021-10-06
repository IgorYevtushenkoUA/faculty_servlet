package com.example.faculty.dao.model.impl;

import com.example.faculty.controller.command.impl.LoginCommand;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDaoImpl implements RoleDao {

    Logger logger = Logger.getLogger(LoginCommand.class.getName());

    private List<Role> parseResultSet(ResultSet rs) {
        List<Role> roles = new ArrayList<>();
        try {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                roles.add(role);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "error in parseResultSet");
        }
        return roles;
    }


    @Override
    public Role findById(int id) {
        Role role = new Role();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_ROLE_BY_ID)) {
            stmt.setInt(1, id);
            role = parseResultSet(stmt.executeQuery()).iterator().next();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findById");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findById");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findById");
        }
        return role;
    }

    @Override
    public Role findByName(String name) {
        Role role = new Role();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_ROLE_BY_NAME)) {
            stmt.setString(1, name);
            role = parseResultSet(stmt.executeQuery()).iterator().next();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException in findByName");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException in findByName");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException in findByName");
        }
        return role;
    }


}
