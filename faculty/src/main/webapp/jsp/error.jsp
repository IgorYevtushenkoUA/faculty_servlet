<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head></head>
<body>

<%@include file="/jsp/components/navbar.jsp" %>

<div class="container">
    <div class="mt-5 justify-content-center">
        <h1><fmt:message key="error.h1"/></h1>
        <h2 class="pt-3"><fmt:message key="error.h2"/></h2>

        <button type="submit" class="btn btn-light btn-lg btn-block">
            <a href="controller?command=courses" ><fmt:message key="error.main"/></a>
        </button>
    </div>
</div>

</body>
</html>
