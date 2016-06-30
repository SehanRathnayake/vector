<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<script src="<c:url value="/resources/js/jquery-3.0.0.min.js"/>"></script>
	<script src="<c:url value="/resources/js/hello.js"/>"></script>
</head>
<body>
	<h1>${message}</h1>
	<button id="testBtn">Click</button>
</body>
</html>