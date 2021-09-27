<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>

<div class="container mt-5">
    <%--    <div class="mt-2">--%>
    <%--        <form th:action="@{/teacher/{courseId} (courseId=${courseId})}" method="get">--%>
    <%--            <div class="input-group p-1">--%>
    <%--                <span class="input-group-text" th:text="#{courseInfo.year}"></span>--%>
    <%--                <input class="form-control" aria-label="With textarea" type="number" name="year"--%>
    <%--                       placeholder="Рік" th:min="2021" th:max="2030">--%>
    <%--            </div>--%>
    <%--            <button type="submit" class="btn btn-primary btn-lg btn-block mt-2">Search</button>--%>
    <%--        </form>--%>
    <%--    </div>--%>

    <div class="mt-5">
        <table class="table border-primary table-bordered">
            <thead>
            <tr>
                <th>#</th>
                <th>Student</th>
                <th>Course</th>
                <th>Recording Time</th>
                <th>Mark</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${studentDto}" varStatus="iter">
                <tr>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="teacher-course" >
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
                        <td><c:out value="${iter.index+1}"/></td>
                        <td>
                            <button type="submit" class="btn btn-primary">Save</button>
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
