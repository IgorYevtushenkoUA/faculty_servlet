package com.example.faculty.dao.model.impl;

import com.example.faculty.dao.model.StatusDao;
import com.example.faculty.db.ConnectionPool;
import com.example.faculty.db.Queries;
import com.example.faculty.model.entity.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class StatusDaoImpl implements StatusDao {
    int i = 1;

    private void prepareStatementForCreateStatus(PreparedStatement ps, Status status) {
        i = 1;
        try {
            ps.setString(i++, status.getName());
        } catch (SQLException e) {
        }
    }

    private List<Status> parseResultSet(ResultSet rs) {
        List<Status> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Status status = new Status();
                status.setId(rs.getInt("id"));
                status.setName(rs.getString("name"));
                list.add(status);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    @Override
    public Status findById(int id) {
        Status status = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_STATUS_BY_ID)) {
            ps.setInt(1,id);
            status = parseResultSet(ps.executeQuery()).iterator().next();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return status;
    }

    @Override
    public Status findByName(String name) {
        Status status = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_STATUS_BY_NAME)) {
            ps.setString(1,name);
            status = parseResultSet(ps.executeQuery()).iterator().next();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException e");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return status;
    }
}
