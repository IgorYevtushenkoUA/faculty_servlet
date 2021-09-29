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
        <input type="hidden" name="command" value="login">
        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="login.email"/><fmt:message
                    key="login.email"/></span>
            <input type="email" class="form-control" name="email" placeholder="<fmt:message
                    key="login.placeholder.email"/>">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="login.password"/><fmt:message
                    key="login.password"/></span>
            <input type="password" class="form-control" name="password" placeholder="<fmt:message
                    key="login.placeholder.password"/>">
        </div>

        <button type="submit" class="btn btn-primary btn-lg btn-block"><fmt:message key="login.login"/></button>
    </form>
</div>

</body>
</html>
