<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

<%@include file="../../components/navbar.jsp"%>

<div class="container mt-5">
    <div class="mt-5">
        <form action="controller" method="get">
            <input type="hidden" name="command" value="students">
            <div class="input-group p-1">
                <span class="input-group-text">Student</span>
                <input class="form-control" aria-label="With textarea" type="text" name="name"
                       placeholder="ПІБ студента">
            </div>
            <button type="submit" class="btn btn-primary btn-lg btn-block mt-2">Search</button>
        </form>
    </div>
    <div class="mt-5">
        <table class="table border-primary table-bordered ">
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Course</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${students}" varStatus="iter">
                <tr>
                    <td>${iter.index+1}</td>
                    <td><a href="controller?command=student-edit&id=${student.getId()}"/>
                            ${student.getLastName()} ${student.getFirstName()} ${student.getSecondName()}
                    </td>
                    <td>${student.getCourseNum()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
