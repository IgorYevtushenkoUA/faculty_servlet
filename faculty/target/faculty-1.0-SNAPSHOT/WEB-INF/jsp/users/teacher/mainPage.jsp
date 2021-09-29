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
    <h1>
        <c:out value="${teacher.getLastName()} ${teacher.getFirstName()} ${teacher.getSecondName()}"/>
    </h1>

    <table class="table border-primary table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="course.course"/></th>
            <th><fmt:message key="course.description"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="course" items="${courses}" varStatus="iter">
            <tr>
                <td><c:out value="${iter.index+1}"/></td>
                <td>
                    <a href="controller?command=teacher-course&id=${course.getId()}">
                        <c:out value="${course.getName()}"/>
                    </a>
                </td>
                <td><c:out value="${course.getDescription()}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
