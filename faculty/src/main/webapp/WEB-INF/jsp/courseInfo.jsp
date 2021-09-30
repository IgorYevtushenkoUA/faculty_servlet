<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head></head>
<body>

<%@include file="/WEB-INF/jsp/components/navbar.jsp" %>

<div class="container mt-5">

    <div class="d-flex justify-content-center">
        <h1><c:out value="${course.getName()}"/></h1>
    </div>

    <div class="mt-2">
        <table class="table table-bordered border-primary">
            <tbody>
            <tr>
                <td><fmt:message key="course.topic"/></td>
                <th><c:out value="${topic.getName()}"/></th>
            </tr>

            <tr>
                <td><fmt:message key="course.capacity"/></td>
                <th><c:out value="${course.getCapacity()}"/></th>
            </tr>

            <tr>
                <td><fmt:message key="course.semester"/></td>
                <th><c:out value="${course.getSemesterStart()}"/></th>
            </tr>

            <tr>
                <td><fmt:message key="course.duration"/></td>
                <th><c:out value="${course.getSemesterDuration()}"/></th>
            </tr>

            <tr>
                <td><fmt:message key="course.description"/></td>
                <th><c:out value="${course.getDescription()}"/></th>
            </tr>

<%--            <tr>--%>
<%--                <td><fmt:message key="course.teacher"/></td>--%>
<%--                <th>TEACHER TODO</th>--%>
<%--            </tr>--%>

            <%--                        <tr>--%>
            <%--                            <td th:text="#{course.teacher}"></td>--%>
            <%--                            <td th:if="${courseInfoDto.getCourse().getTeacher()} != null"--%>
            <%--                                th:text="${courseInfoDto.getCourse().getTeacher().getLastName()} + ' ' + ${courseInfoDto.getCourse().getTeacher().getFirstName()} + ' ' + ${courseInfoDto.getCourse().getTeacher().getSecondName()}"/>--%>
            <%--                            <td th:if="${courseInfoDto.getCourse().getTeacher()}== null" th:text="#{courses.without_teacher}"/>--%>
            <%--                        </tr>--%>

            </tbody>
        </table>
    </div>

    <table class="table table-bordered table-responsive-sm">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="course.name"/></th>
            <th><fmt:message key="course.course"/></th>
            <th><fmt:message key="course.recordTime"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${studentDto}" varStatus="iter">
        <tr>
            <td><c:out value="${iter.index+1}"/></td>
            <td>
                <c:out value="${student.getStudent().getLastName()} ${student.getStudent().getFirstName()} ${student.getStudent().getSecondName()}"/>
            </td>
            <td><c:out value="${student.getStudent().getCourseNum()}"/></td>
            <td><c:out value="${student.getRecordingTime()}"/></td>
                <%--            </tr>--%>
            </c:forEach>
        </tbody>
    </table>


    <c:if test="${role eq 'ROLE_GUEST'}">
        <div>
            <a class="btn btn-primary btn-lg btn-block" href="controller?command=login" role="button"><fmt:message key="course.login"/></a>
        </div>
    </c:if>

    <c:if test="${role eq 'ROLE_ADMIN'}">
        <div>
            <a class="btn btn-primary btn-lg btn-block"
               href="controller?command=course-edit&id=${course.getId()}"><fmt:message key="courseInfo.edit"/></a>
        </div>
    </c:if>

    <c:if test="${role eq 'ROLE_STUDENT' && canEnroll}">
        <div>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="course">
                <input type="hidden" name="courseId" value="${course.getId()}">
                <input type="hidden" name="studentId" value="${studentId}">
                <c:if test="${!doEnroll}">
                    <button type="submit" class="btn btn-primary btn-lg btn-block" name="action" value="enroll">
                        <fmt:message key="course.enroll"/>
                    </button>
                </c:if>
                <c:if test="${doEnroll}">
                    <button type="submit" class="btn btn-primary btn-lg btn-block" name="action" value="drop_out">
                        <fmt:message key="course.drop_out"/>
                    </button>
                </c:if>
            </form>
        </div>
    </c:if>

</div>
</body>
</html>
