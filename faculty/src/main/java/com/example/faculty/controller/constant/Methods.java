package com.example.faculty.controller.constant;

import com.example.faculty.dao.model.RoleDao;
import com.example.faculty.dao.model.impl.RoleDaoImpl;
import com.example.faculty.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Methods {

    public static String getRole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RoleDao roleDao = new RoleDaoImpl();
        User user = (User) session.getAttribute("user");
        if (user == null) return "ROLE_GUEST";
        return roleDao.findById(user.getRoleId()).getName();
    }

}
