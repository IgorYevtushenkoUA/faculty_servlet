<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="courses">
            <div class="">
                <div class="input-group p-1">
                    <span class="input-group-text">Course</span>
                    <input class="form-control" aria-label="With textarea" type="text" name="course"
                           placeholder="Назва дисципліни">
                </div>

                <div class="input-group p-1">
                    <span class="input-group-text">Duration</span>
                    <input class="form-control" aria-label="With textarea" type="number" name="duration"
                           placeholder="Тривалість" min="1" max="2">
                </div>
                <div class="input-group p-1">
                    <span class="input-group-text">Capacity</span>
                    <input class="form-control" aria-label="With textarea" type="number" name="capacity"
                           placeholder="Кількість студентів" min="10" max="150">
                </div>

                <div class="input-group p-1">
                    <span class="input-group-text">Topic</span>
                    <input class="form-control" aria-label="With textarea" type="text" name="topic"
                           placeholder="Кількість студентів">
                </div>

                <div class="input-group p-1">
                    <span class="input-group-text">Teacher</span>
                    <input class="form-control" aria-label="With textarea" type="text" name="teacher"
                           placeholder="ПІБ викладача">
                </div>
            </div>
            <%--            <button type="submit" class="btn btn-primary btn-lg btn-block">Filter</button>--%>
            <input type="submit" class="btn btn-primary btn-lg btn-block" value="Filter">
        </form>
    </div>

    <div class="col-lg-10 mt-5 mb-5">
        <table class="table table-bordered table-responsive-sm">
            <thead>
            <tr>
                <th>#</th>
                <th>Title</th>
                <th>Description</th>
                <th>Capacity</th>
                <th>Semester</th>
                <th>Duration</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td><c:out value="${course.getId()}"/></td>
                    <td>
                        <a href="/controller?command=course&courseId=${course.getId()}">
                            <c:out value="${course.getName()}"/>
                        </a>
                    </td>
                    <td><c:out value="${course.getDescription()}"/></td>
                    <td><c:out value="${course.getCapacity()}"/></td>
                    <td><c:out value="${course.getSemesterStart()}"/></td>
                    <td><c:out value="${course.getSemesterDuration()}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>


<script src="@{/webjars/jquery/jquery.min.js}"></script>
<script src="@{/webjars/popper.js/umd/popper.min.js}"></script>
<script src="@{/webjars/bootstrap/js/bootstrap.min.js}"></script>
</body>
</html>