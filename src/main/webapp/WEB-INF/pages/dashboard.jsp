<%@ include file="header.jsp" %>

<div>
    Hi, <security:authentication property="principal.fullname" />.
    <a href="${logoutUrl}">Log out</a>
    <h1>
       Dashboard
    </h1>
</div>
<script src="<c:url value="/resources/js/dashboard.js" />"></script>

</body>
</html>