<%@ include file="header.jsp" %>
<div>
    <c:url var="homeUrl" value="/home"/>
    <c:url var="logoutUrl" value="/j_spring_security_logout"/>
    <c:url var="postLoginUrl" value="/j_spring_security_check"/>

    <security:authorize access="isAnonymous()">
        <%@ include file="login.jsp" %>
    </security:authorize>

    <security:authorize access="isAuthenticated()">
        <%@ include file="dashboard.jsp" %>
    </security:authorize>

</div>

<script src="<c:url value="/resources/js/home.js" />"></script>
</body>
</html>