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

    <%@include file="/jsp/components/createPanel.jsp" %>

    <div class="mt-5">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="teacher-create">

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="teacherRegister.firstName"/></span>
                <input type="text" class="form-control" name="firstName" placeholder="<fmt:message key="register.placeholder.firstName"/>">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="teacherRegister.secondName"/>SecondName</span>
                <input type="text" class="form-control" name="secondName" placeholder="<fmt:message key="register.placeholder.secondName"/>">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="teacherRegister.lastName"/>LastName</span>
                <input type="text" class="form-control" name="lastName" placeholder="<fmt:message key="register.placeholder.lastName"/>">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="teacherRegister.email"/>Email</span>
                <input type="email" class="form-control" name="email" placeholder="<fmt:message key="register.placeholder.email"/>">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text"><fmt:message key="teacherRegister.password"/>Password</span>
                <input type="password" class="form-control" name="password" placeholder="<fmt:message key="register.placeholder.password"/>">
            </div>

            <button type="submit" class="btn btn-light btn-lg btn-block"><fmt:message key="teacherList.create"/>Create</button>
        </form>
    </div>
</div>
</body>
</html>
