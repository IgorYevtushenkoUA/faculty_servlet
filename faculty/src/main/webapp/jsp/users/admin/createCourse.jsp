<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">

    <%@include file="/jsp/components/createPanel.jsp"%>
    <%@include file="../../components/navbar.jsp"%>

    <div class="mt-5">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="course-create" >

            <div class="input-group p-1">
                <span class="input-group-text">Course</span>
                <input class="form-control" aria-label="With textarea" type="text" name="course"
                       placeholder="Назва дисципліни">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text">Topic</span>
                <select name="topicId" class="custom-select">
                    <c:forEach items="${topics}" var="topic">
                        <option value="${topic.getId()}">${topic.getName()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="input-group p-1">
                <span class="input-group-text">Capacity</span>
                <input class="form-control" aria-label="With textarea" type="number" name="capacity"
                       placeholder="Кількість студентів" min="10" max="150">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text">Semester</span>
                <input class="form-control" aria-label="With textarea" type="number" min="1" max="8" name="semesterStart"
                       placeholder="Semester Start">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text">Duration</span>
                <input class="form-control" aria-label="With textarea" type="number" name="duration"
                       placeholder="Тривалість" min="1" max="2">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text">Description</span>
                <input class="form-control" aria-label="With textarea" type="text" name="description"
                       placeholder="Description">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text">Teacher</span>
                <select name="teacherId" class="custom-select">
                    <c:forEach items="${teachers}" var="t">
                        <option value="${t.getId()}">${t.getLastName()} ${t.getFirstName()} ${t.getSecondName()}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-light btn-lg btn-block">Create</button>
        </form>
    </div>
</div>

</body>
</html>
