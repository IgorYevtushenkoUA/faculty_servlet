<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head></head>
<body>

<div class="container mt-5">

    <h1>${student.getLastName()} ${student.getFirstName()} ${student.getSecondName()}</h1>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="student-edit">
        <input type="hidden" name="id" value="${student.getId()}">

        <button type="submit" class="btn btn-secondary">
            <c:if test="${student.isEnable()}"><fmt:message key="studentInfo.block"/></c:if>
            <c:if test="${!student.isEnable()}"><fmt:message key="course.drop_out"/></c:if>
        </button>
    </form>
</div>

</body>
</html>
