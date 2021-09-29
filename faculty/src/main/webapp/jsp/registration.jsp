<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="/jsp/components/navbar.jsp"%>

<h1>Register Form</h1>
<form action="controller" method="post">
    <input type="hidden" name="command" value="register">
    <table style="with: 50%">
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName" value="firstName"/></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastName" value="lastName"/></td>
        </tr>
        <tr>
            <td>Second Name</td>
            <td><input type="text" name="secondName" value="secondName"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="email@gmail.com"/></td>
        </tr>
        <tr>
            <td>Course</td>
            <td><input type="number" name="course" value="4"/></td>
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