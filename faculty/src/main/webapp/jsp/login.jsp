<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="/jsp/components/navbar.jsp" %>

<div class="container mt-5">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="login">
        <div class="input-group p-1">
            <span class="input-group-text">Email</span>
            <input type="email" class="form-control" name="email" placeholder="Enter Email">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text">Password</span>
            <input type="password" class="form-control" name="password" placeholder="Enter Password">
        </div>

        <button type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
    </form>
</div>

</body>
</html>
