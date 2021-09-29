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
    <form action="controller" method="post">
        <input type="hidden" name="command" value="register">

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="register.firstName"/></span>
            <input type="text" class="form-control" name="firstName" placeholder="<fmt:message key="register.placeholder.firstName"/>"}>
        </div>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="register.secondName"/></span>
            <input type="text" class="form-control" name="secondName" placeholder="<fmt:message key="register.placeholder.secondName"/>">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="register.lastName"/></span>
            <input type="text" class="form-control" name="lastName" placeholder="<fmt:message key="register.placeholder.lastName"/>">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="register.email"/></span>
            <input type="email" class="form-control" name="email" placeholder="<fmt:message key="register.placeholder.email"/>">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="register.password"/></span>
            <input type="password" class="form-control" name="password" placeholder="<fmt:message key="register.placeholder.password"/>">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="register.course"/></span>
            <input type="number" name="course" class="form-control" min="1" max="4" placeholder="<fmt:message key="register.placeholder.course"/>">
        </div>

        <button type="submit" class="btn btn-primary btn-lg btn-block"><fmt:message key="register.register"/></button>
    </form>
</div>

</body>
</html>