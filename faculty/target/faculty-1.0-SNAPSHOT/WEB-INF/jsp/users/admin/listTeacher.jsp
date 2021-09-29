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
    <div class="mt-5">
        <form action="controller" method="get">
            <input type="hidden" name="command" value="students">
            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="courseInfo.student"/>Student</span>
                <input class="form-control" aria-label="With textarea" type="text" name="name"
                       placeholder="<fmt:message key="courses.placeholder.student"/>">
            </div>
            <button type="submit" class="btn btn-primary btn-lg btn-block mt-2"><fmt:message key="teacherList.search"/></button>
        </form>
    </div>
    <div class="mt-5">
        <table class="table border-primary table-bordered ">
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="teacher" items="${teachers}" varStatus="iter">
                <tr>
                    <td>${iter.index+1}</td>
                    <td><a href="controller?command=teacher-edit&id=${teacher.getId()}"/>
                            ${teacher.getLastName()} ${teacher.getFirstName()} ${teacher.getSecondName()}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
