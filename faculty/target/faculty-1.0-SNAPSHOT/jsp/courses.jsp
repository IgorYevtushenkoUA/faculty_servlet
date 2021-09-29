<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head></head>
<body>

<%@include file="/jsp/components/navbar.jsp" %>

<div class="container">
    <div>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="courses">
            <div class="">
                <div class="input-group p-1">
                    <span class="input-group-text"><fmt:message key="courses.course"/></span>
                    <input class="form-control" aria-label="With textarea" type="text" name="course"
                           placeholder="<fmt:message key="courses.placeholder.course"/>" >

                    <div>
                        <input id="asc" type="radio" name="sortType" value="ASC"
                               <c:if test="${sortType eq 'ASC'}">checked</c:if> hidden/>
                        <label for="asc" id="ascl" class="${classes.get(0)}" onclick="setASC()"><fmt:message
                                key="courses.asc"/></label>
                    </div>

                    <div>
                        <input id="desc" type="radio" name="sortType" value="DESC"
                               <c:if test="${sortType eq 'DESC'}">checked</c:if> hidden/>
                        <label for="desc" id="descl" class="${classes.get(1)}" onclick="setDESC()"><fmt:message
                                key="courses.desc"/></label>
                    </div>

                </div>

                <div class="input-group p-1">
                    <span class="input-group-text"><fmt:message key="courses.duration"/></span>
                    <input class="form-control" aria-label="With textarea" type="number" name="duration"
                           placeholder="<fmt:message key="courses.placeholder.duration"/>" min="1" max="2">
                </div>
                <div class="input-group p-1">
                    <span class="input-group-text"><fmt:message key="courses.capacity"/></span>
                    <input class="form-control" aria-label="With textarea" type="number" name="capacity"
                           placeholder="<fmt:message key="courses.placeholder.capacity"/>" min="10" max="150">
                </div>

                <div class="input-group p-1">
                    <span class="input-group-text"><fmt:message key="courses.topic"/></span>
                    <input class="form-control" aria-label="With textarea" type="text" name="topic"
                           placeholder="<fmt:message key="courses.placeholder.topic"/>">
                </div>

                <div class="input-group p-1">
                    <span class="input-group-text"><fmt:message key="courses.teacher"/></span>
                    <input class="form-control" aria-label="With textarea" type="text" name="teacher"
                           placeholder="<fmt:message key="courses.placeholder.teacher"/>">
                </div>
            </div>
            <button type="submit" class="btn btn-primary btn-lg btn-block"><fmt:message key="courses.filter"/></button>
        </form>
    </div>

    <div class="col-lg-10 mt-5 mb-5">
        <table class="table table-bordered table-responsive-sm">
            <thead>
            <tr>
                <th>#</th>
                <th><fmt:message key="courses.title"/></th>
                <th><fmt:message key="courses.description"/></th>
                <th><fmt:message key="courses.capacity"/></th>
                <th><fmt:message key="courses.semester"/></th>
                <th><fmt:message key="courses.duration"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courses}" varStatus="iter">
                <tr>
                    <td><c:out value="${iter.index+1}"/></td>
                    <td>
                        <a href="/controller?command=course&courseId=${course.getId()}">
                            <c:out value="${course.getName()}"/>
                        </a>
                    </td>
                    <td><c:out value="${course.getDescription()}"/></td>
                    <td><c:out value="${course.getCapacity()}"/></td>
                    <td><c:out value="${course.getSemesterStart()}"/></td>
                    <td><c:out value="${course.getSemesterDuration()}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<script>

    function setASC() {
        document.getElementById("ascl").classList.remove("btn-outline-primary");
        document.getElementById("ascl").classList.add("btn-primary");
        document.getElementById("descl").classList.remove("btn-danger");
        document.getElementById("descl").classList.add("btn-outline-danger");
    }

    function setDESC() {
        document.getElementById("ascl").classList.remove("btn-primary");
        document.getElementById("ascl").classList.add("btn-outline-primary");
        document.getElementById("descl").classList.remove("btn-outline-danger");
        document.getElementById("descl").classList.add("btn-danger");
    }

</script>

<script src="@{/webjars/jquery/jquery.min.js}"></script>
<script src="@{/webjars/popper.js/umd/popper.min.js}"></script>
<script src="@{/webjars/bootstrap/js/bootstrap.min.js}"></script>
</body>
</html>