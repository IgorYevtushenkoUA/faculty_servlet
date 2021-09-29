<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="/jsp/components/navbar.jsp"%>

<h1>Login Form</h1>
<form action="controller" method="post">
    <input type="hidden" name="command" value="login">
    <table style="with: 50%">
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="s@gmail.com"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value="p"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
