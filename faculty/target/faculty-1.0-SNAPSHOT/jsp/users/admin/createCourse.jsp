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

<div class="container mt-5">

    <%@include file="/jsp/components/createPanel.jsp"%>

    <div class="mt-5">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="course-create" >

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="course.course"/></span>
                <input class="form-control" aria-label="With textarea" type="text" name="course"
                       placeholder="<fmt:message key="courses.placeholder.course"/>">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="course.topic"/></span>
                <select name="topicId" class="custom-select">
                    <c:forEach items="${topics}" var="topic">
                        <option value="${topic.getId()}">${topic.getName()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="course.capacity"/></span>
                <input class="form-control" aria-label="With textarea" type="number" name="capacity"
                       placeholder="<fmt:message key="courses.placeholder.capacity"/>" min="10" max="150">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="course.semester"/></span>
                <input class="form-control" aria-label="With textarea" type="number" min="1" max="8" name="semesterStart"
                       placeholder="<fmt:message key="courses.placeholder.semester"/>">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="course.duration"/></span>
                <input class="form-control" aria-label="With textarea" type="number" name="duration"
                       placeholder="<fmt:message key="courses.placeholder.duration"/>" min="1" max="2">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="course.description"/></span>
                <input class="form-control" aria-label="With textarea" type="text" name="description"
                       placeholder="<fmt:message key="courses.placeholder.description"/>">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="course.teacher"/></span>
                <select name="teacherId" class="custom-select">
                    <c:forEach items="${teachers}" var="t">
                        <option value="${t.getId()}">${t.getLastName()} ${t.getFirstName()} ${t.getSecondName()}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-light btn-lg btn-block"><fmt:message key="courseCreate.create"/></button>
        </form>
    </div>
</div>

</body>
</html>
