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
  <div class="mt-5 text-center">
    <h3>CREATE</h3>
  </div>
  <%@include file="/jsp/components/createPanel.jsp"%>
</div>

</body>
</html>
