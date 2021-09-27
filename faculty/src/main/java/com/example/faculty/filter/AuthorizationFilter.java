package com.example.faculty.filter;

import com.example.faculty.controller.constant.PageConstants;
import com.example.faculty.dao.model.*;
import com.example.faculty.dao.model.impl.*;
import com.example.faculty.model.entity.Teacher;
import com.example.faculty.model.enums.ROLE;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AuthorizationFilter implements Filter {

    private static final Map<ROLE, List<String>> accessMap = new HashMap<>();
    private static final List<String> forAll = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        doTest();
        accessMap.put(ROLE.ROLE_ADMIN, asList(filterConfig.getInitParameter(ROLE.ROLE_ADMIN.name())));
        accessMap.put(ROLE.ROLE_TEACHER, asList(filterConfig.getInitParameter(ROLE.ROLE_TEACHER.name())));
        accessMap.put(ROLE.ROLE_STUDENT, asList(filterConfig.getInitParameter(ROLE.ROLE_STUDENT.name())));
        forAll.addAll(asList(filterConfig.getInitParameter(ROLE.ROLE_GUEST.name())));
    }

    private static void doTest() {
        System.out.println("----- START ---");
        CourseDao courseDao = new CourseDaoImpl();
        RoleDao roleDao = new RoleDaoImpl();
        StatusDao statusDao = new StatusDaoImpl();
        StudentHasCourseDao studentHasCourseDao = new StudentHasCourseDaoImpl();
        TopicDao topicDao = new TopicDaoImpl();
        UserDao userDao = new UserDaoImpl();

        System.out.println(userDao.findAllEnrolledStudentToCourse(1));

        System.out.println("----- END -----");
    }

    private boolean isAllowed(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String command = request.getParameter("command");
        System.out.println("command : [" + command + "]");
        if (command == null || command.isEmpty()) {
            return false;
        }
        if (forAll.contains(command)) {
            return true;
        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        ROLE role = (ROLE) session.getAttribute("role");
        return accessMap.get(role).contains(command);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter man");
        if (isAllowed(servletRequest)) {
            System.out.println("isAllow");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            System.out.println("not isAllow");
            String errorMessage = "You do not have permission to access the requested command!";
            servletRequest.setAttribute("error_message", errorMessage);
            servletRequest.getRequestDispatcher(PageConstants.ERROR).forward(servletRequest, servletResponse);
        }
    }

    private List<String> asList(String source) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(source);
        while (st.hasMoreElements()) {
            list.add(st.nextToken());
        }
        return list;
    }

}
