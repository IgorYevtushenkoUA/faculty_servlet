<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head></head>
<body>

<%@include file="../../components/navbar.jsp"%>

<div class="container mt-5 mb-5">
    <form action="controller" method="get" class="row">
        <input type="hidden" name="command" value="student">
        <div class="input-group">
            <select class="custom-select" name="type">

                <option value="NOT_STARTED" <c:if test="${type eq 'NOT_STARTED'}">selected="selected"</c:if>>Not
                    Started
                </option>
                <option value="IN_PROGRESS"
                        <c:if test="${type eq 'IN_PROGRESS'}">selected="selected"</c:if> >In Progress
                </option>
                <option value="FINISHED" <c:if test="${type eq 'FINISHED'}">selected="selected"</c:if>>Finished</option>
            </select>
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit"><fmt:message key="studentList.search"/></button>
            </div>
        </div>
    </form>

    <table class="table mt-2">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="studentList.name"/></th>
            <th><fmt:message key="studentPersonalPage.mark"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="course" items="${courses}" varStatus="iter">
            <tr>
                <td><c:out value="${iter.index+1}"/></td>
                    <%--                <td th:if="${type}=='NOT_STARTED'"><a--%>
                    <%--                        th:href="@{/courses/{courseId} (courseId=${course.getCourseId()})}"--%>
                    <%--                        th:text="${course.getCourseName()}"></a>--%>
                    <%--                </td>--%>
                    <%--                <td th:if="${type}!='NOT_STARTED'" th:text="${course.getCourseName()}"></td>--%>
                <td><c:out value="${course.getCourseName()}"/></td>
                <td><c:out value="${course.getMark()}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
