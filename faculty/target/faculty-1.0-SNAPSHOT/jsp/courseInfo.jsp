<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">

    <div class="d-flex justify-content-center">
        <h1><c:out value="${course.getName()}"/></h1>
    </div>

    <div class="mt-2">
        <table class="table table-bordered border-primary">
            <tbody>
            <tr>
                <td>Topic</td>
                <th><c:out value="${topic.getName()}"/></th>
            </tr>

            <tr>
                <td>Capacity</td>
                <th><c:out value="${course.getCapacity()}"/></th>
            </tr>

            <tr>
                <td>Semester</td>
                <th><c:out value="${course.getSemesterStart()}"/></th>
            </tr>

            <tr>
                <td>Duration</td>
                <th><c:out value="${course.getSemesterDuration()}"/></th>
            </tr>

            <tr>
                <td>Description</td>
                <th><c:out value="${course.getDescription()}"/></th>
            </tr>

            <tr>
                <td>Teacher</td>
                <th>TEACHER TODO</th>
            </tr>

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
            <th>Name</th>
            <th>Course Num</th>
            <th>Recording time</th>
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
</div>
</body>
</html>
