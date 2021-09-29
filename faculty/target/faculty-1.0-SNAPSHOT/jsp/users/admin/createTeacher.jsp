<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

<%@include file="../../components/navbar.jsp"%>

<div class="container mt-5">

    <%@include file="/jsp/components/createPanel.jsp" %>

    <div class="mt-5">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="teacher-create">

            <div class="input-group p-1">
                <span class="input-group-text">FirstName</span>
                <input type="text" class="form-control" name="firstName" placeholder="Enter First Name">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text">SecondName</span>
                <input type="text" class="form-control" name="secondName" placeholder="Enter Second Name">
            </div>

            <div class="input-group p-1">
                <span class="input-group-text">LastName</span>
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

            <button type="submit" class="btn btn-light btn-lg btn-block">Create</button>
        </form>
    </div>
</div>
</body>
</html>
