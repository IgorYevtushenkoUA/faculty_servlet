<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head></head>
<body>
<div>
    <div fragment="navbar"
         class="navbar navbar-light bg-light">


        <div class="d-flex justify-content-lg-start">
            <a class="navbar-brand" href="/">Faculty </a>
            <ul class="nav ">
                <li class="nav-item active">
                    <a class="nav-link" href="controller?command=courses"><fmt:message key="navbar.courses"/></a>
                </li>
            </ul>
        </div>

        <div class="d-flex justify-content-end ">
            <div>
                <ul class="nav  justify-content-end">
                    <li class="nav-item">
                        <a class="nav-link" href="?lang=en">
                            EN
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="?lang=ua">
                            UA
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=login"><fmt:message key="login.login"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=register"><fmt:message key="register.register"/></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<img src="https://wittysparks.com/wp-content/uploads/2020/10/tips-to-choose-the-right-college.jpg"
     alt="image"  width="100%" height="90%"/>


</body>
</html>