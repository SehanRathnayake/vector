<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="<c:url value="/resources/js/hello.js" />"></script></head>
<body>
	<h1>${message}</h1>
	<button id="testBtn">Click</button>
</body>
</html>