<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">

    <%@include file="/jsp/components/createPanel.jsp" %>

    <div class="mt-5">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="student-create">

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

            <div class="input-group p-1">
                <span class="input-group-text">Course</span>
                <input type="number" name="courseNum" class="form-control" min="1" max="4">
            </div>

            <button type="submit" class="btn btn-light btn-lg btn-block">Create</button>
        </form>
    </div>
</div>
</body>
</html>
