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
    <h1>
        <c:out value="${teacher.getLastName()} ${teacher.getFirstName()} ${teacher.getSecondName()}"/>
    </h1>

    <table class="table border-primary table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th>Course Name</th>
            <th>Description</th>
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
