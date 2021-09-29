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

    <%@include file="/WEB-INF/jsp/components/createPanel.jsp" %>

    <div class="mt-5">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="student-create">

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="studentRegister.firstName"/>FirstName</span>
                <input type="text" class="form-control" name="firstName" placeholder="<fmt:message key="register.placeholder.firstName"/>">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="studentRegister.secondName"/></span>
                <input type="text" class="form-control" name="secondName" placeholder="<fmt:message key="register.placeholder.secondName"/>">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="studentRegister.lastName"/></span>
                <input type="text" class="form-control" name="lastName" placeholder="<fmt:message key="register.placeholder.lastName"/>">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="studentRegister.email"/></span>
                <input type="email" class="form-control" name="email" placeholder="<fmt:message key="register.placeholder.email"/>">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="studentRegister.password"/></span>
                <input type="password" class="form-control" name="password" placeholder="<fmt:message key="register.placeholder.password"/>">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="studentRegister.course"/></span>
                <input type="number" name="courseNum" class="form-control" min="1" max="4">
            </div>

            <button type="submit" class="btn btn-light btn-lg btn-block"><fmt:message key="courseCreate.create"/></button>
        </form>
    </div>
</div>
</body>
</html>
