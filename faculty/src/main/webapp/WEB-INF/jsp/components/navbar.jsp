<div fragment="navbar"
     class="navbar navbar-light bg-light">


    <div class="d-flex justify-content-lg-start">
        <a class="navbar-brand" href="/">Faculty </a>
        <ul class="nav ">
            <li class="nav-item active">
                <a class="nav-link" href="controller?command=courses"><fmt:message key="navbar.courses"/></a>
            </li>
        </ul>
    </div>


    <c:if test="${role eq 'ROLE_ADMIN'}">
        <div class=" d-flex justify-content-center">
            <ul class="nav  justify-content-center">
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=teachers"><fmt:message key="creationPanel.teachers"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=students"><fmt:message key="creationPanel.students"/></a>
                </li>
            </ul>
        </div>
    </c:if>


    <div class="d-flex justify-content-end ">
        <c:if test="${role eq 'ROLE_GUEST'}">
            <div>
                <ul class="nav  justify-content-end">
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-item nav-link active"--%>
<%--                           ><input type="hidden" name="lang" value="en">--%>
<%--                            EN--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-item nav-link active"--%>
<%--                        ><input type="hidden" name="lang" value="ua">--%>
<%--                            UA--%>
<%--                        </a>--%>
<%--                    </li>--%>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=login"><fmt:message key="login.login"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=register"><fmt:message key="register.register"/></a>
                    </li>
                </ul>
            </div>
        </c:if>

        <c:if test="${role eq 'ROLE_ADMIN' || role eq 'ROLE_TEACHER' || role eq 'ROLE_STUDENT' }">
            <div>
                <ul class="nav  justify-content-end">
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="?lang=en">--%>
<%--                            EN--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="?lang=ua">--%>
<%--                            UA--%>
<%--                        </a>--%>
<%--                    </li>--%>
                    <li class="nav-item">
                        <a class="nav-link"
                                <c:if test="${role eq 'ROLE_ADMIN'}"> href="controller?command=admin"</c:if>
                                <c:if test="${role eq 'ROLE_TEACHER'}"> href="controller?command=teacher"</c:if>
                                <c:if test="${role eq 'ROLE_STUDENT'}"> href="controller?command=student"</c:if> >
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                 class="bi bi-person-circle" viewBox="0 0 16 16">
                                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                <path fill-rule="evenodd"
                                      d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                            </svg>
                        </a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="controller?command=logout"><fmt:message key="navbar.logout"/></a></li>
                </ul>
            </div>
        </c:if>
    </div>

</div>
</body>
</html>
