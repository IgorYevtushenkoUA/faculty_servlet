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

    <div class="mt-5">
        <table class="table border-primary table-bordered">
            <thead>
            <tr>
                <th>#</th>
                <th><fmt:message key="courseInfo.student"/></th>
                <th><fmt:message key="courseInfo.course"/></th>
                <th><fmt:message key="courseInfo.recordTime"/></th>
                <th><fmt:message key="courseInfo.mark"/></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${studentDto}" varStatus="iter">
                <tr>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="teacher-course">
                        <input value="${id}" name="courseId" hidden>
                        <input value="${student.getStudent().getId()}" name="studentId" hidden>

                        <td><c:out value="${iter.index+1}"/></td>
                        <td>
                            <c:out value="${student.getStudent().getLastName()} ${student.getStudent().getFirstName()} ${student.getStudent().getSecondName()}"/>
                        </td>
                        <td>
                            <c:out value="${student.getStudent().getCourseNum()}"/>
                        </td>
                        <td>
                            <c:out value="${student.getRecordingTime()}"/>
                        </td>
                        <td>
                            <input type="number" value="${student.getMark()}" name="mark" min="1" max="100"/>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-primary"><fmt:message key="courseInfo.save"/></button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
