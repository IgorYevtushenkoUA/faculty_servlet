<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="/jsp/components/navbar.jsp" %>

<div class="container mt-5">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="register">

        <div class="input-group p-1">
            <span class="input-group-text">First Name</span>
            <input type="text" class="form-control" name="firstName" placeholder="Enter First Name">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text">Second Name</span>
            <input type="text" class="form-control" name="secondName" placeholder="Enter Second Name">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text">Last Name</span>
            <input type="text" class="form-control" name="lastName" placeholder="Enter Last Name">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text">Email</span>
            <input type="email" class="form-control" name="email" placeholder="Enter Email">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text">Password</span>
            <input type="password" class="form-control" name="password" placeholder="Enter Password">
        </div>

        <div class="input-group p-1">
            <span class="input-group-text">Course</span>
            <input type="number" name="course" class="form-control" min="1" max="4">
        </div>

        <button type="submit" class="btn btn-primary btn-lg btn-block">Register</button>
    </form>
</div>

</body>
</html>