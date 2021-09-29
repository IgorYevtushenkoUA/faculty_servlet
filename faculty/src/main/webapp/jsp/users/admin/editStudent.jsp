<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

<%@include file="../../components/navbar.jsp"%>

<div class="container mt-5">

    <h1>${student.getLastName()} ${student.getFirstName()} ${student.getSecondName()}</h1>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="student-edit">
        <input type="hidden" name="id" value="${student.getId()}">

        <button type="submit" class="btn btn-secondary">
            <c:if test="${student.isEnable()}">Блокувати</c:if>
            <c:if test="${!student.isEnable()}">Розблокувати</c:if>
        </button>
    </form>
</div>

</body>
</html>
