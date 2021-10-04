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
    <form action="controller" method="post" validate(this)>
        <input type="hidden" name="command" value="login">
        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="login.email"/></span>
            <input type="email" id="email" class="form-control" name="email" placeholder="<fmt:message
                    key="login.placeholder.email"/>">
        </div>
<%--        <div id="emailErr" class="text-danger">--%>
<%--            <span>${emailErr}</span>--%>
<%--        </div>--%>

        <div class="input-group p-1">
            <span class="input-group-text"><fmt:message key="login.password"/></span>
            <input type="password" class="form-control" name="password" id="password"
                   placeholder="<fmt:message key="login.placeholder.password"/>">
        </div>

        <div class="text-danger" id="messages">
            <span>${messages}</span>
        </div>

        <button type="submit" onclick="validForm()" class="btn btn-primary btn-lg btn-block"><fmt:message
                key="login.login"/></button>
    </form>
</div>

<script>
    function validForm() {
        var emailI = document.getElementById("email").value;
        var passwordI = document.getElementById("password");
        let emailPatter = /^[a-zA-Z0-9.]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*/;
        if (emailPatter.test(emailI)) {
            document.getElementById("messages").innerHTML = "<input type='hidden' name='messages' value='' />"
        }else{
            document.getElementById("messages").innerHTML = "<input type='hidden' name='messages' value='emailIncorrect' />"
        }
    }

</script>

</body>
</html>
