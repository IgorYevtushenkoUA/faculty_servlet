<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head></head>
<body>

<%@include file="../../components/navbar.jsp"%>

<div class="container p-3">

    <div class="d-flex justify-content-center">
        <h1><fmt:message key="courseInfo.edit"/></h1>
    </div>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="course-edit">
        <input type="hidden" name="id" value="${course.getId()}">

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="course.name"/></span>
            <input class="form-control" aria-label="With textarea" type="text" name="name"
                   value="${course.getName()}">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="courses.topic"/></span>
            <select name="topicId" class="custom-select">
                <c:forEach items="${topics}" var="topic">
                    <option value="${topic.getId()}">${topic.getName()}</option>
                </c:forEach>
            </select>
        </div>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="courses.capacity"/></span>
            <input class="form-control" aria-label="With textarea" type="number" min="10" max="150"
                   name="capacity"
                   value="${course.getCapacity()}">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="courses.semester"/></span>
            <input class="form-control" aria-label="With textarea" type="number" min="1" max="8"
                   name="semesterStart"
                   value="${course.getSemesterStart()}">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="courses.duration"/></span>
            <input class="form-control" aria-label="With textarea" type="number" min="1" max="2"
                   name="semesterDuration"
                   value="${course.getSemesterDuration()}">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="courses.description"/></span>
            <input class="form-control" aria-label="With textarea" type="text" name="description"
                   value="${course.getDescription()}">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="courses.teacher"/></span>
            <select name="teacherId" class="custom-select">
                <c:forEach items="${teachers}" var="t">
                    <option value="${t.getId()}">${t.getLastName()} ${t.getFirstName()} ${t.getSecondName()}</option>
                </c:forEach>
            </select>
        </div>

        <div class="input-group p-1">
            <button type="submit" class="btn btn-success" name="action" value="save">
                <fmt:message key="courseInfo.save"/>
            </button>
            <button type="submit" class="btn btn-danger" name="action"
                    value="delete"><fmt:message key="courseInfo.delete"/>
            </button>
        </div>
    </form>

</div>

</body>
</html>
