<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head></head>
<body>

<%@include file="../../components/navbar.jsp" %>

<div class="container mt-5">

    <div class="d-flex justify-content-center">
        <h1>${teacher.getLastName()} ${teacher.getFirstName()} ${teacher.getSecondName()}</h1>
    </div>

    <table class="table border-primary table-bordered">
        <thead>
        <tr>
            <td>#</td>
            <td><fmt:message key="course.course"/></td>
            <td></td>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${teacherCourses}" var="course" varStatus="iter">
            <tr>
                <td>${iter.index+1}</td>
                <td><c:out value="${course.getName()}"/></td>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="teacher-edit">
                        <input type="hidden" name="id" value="${teacher.getId()}">
                        <input type="hidden" name="courseId" value="${course.getId()}">
                        <input type="hidden" name="action" value="delete">
                        <button class="btn btn-danger"><fmt:message key="courseInfo.delete"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form action="controller" method="post">

        <div class="input-group">
            <span class="input-group-text">Course</span>
            <select name="courseId" class="custom-select">
                <c:forEach items="${freeCourses}" var="course">

                    <option value="${course.getId()}">
                            ${course.getName()}
                        <input type="hidden" name="command" value="teacher-edit">
                        <input type="hidden" name="id" value="${teacher.getId()}">
                        <input type="hidden" name="courseId" value="${course.getId()}">
                        <input type="hidden" name="action" value="add">
                    </option>
                </c:forEach>

            </select>
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit"><fmt:message key="teacherInfo.add"/></button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
