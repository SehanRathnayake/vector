<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <script src="<c:url value="/resources/js/jquery/jquery-3.0.0.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/animate.css" />">

    <!-- Optional theme -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css" />">

    <%-- custom styles --%>
    <link rel="stylesheet" href="<c:url value="/resources/css/datepicker.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-touchspin.css" />">
    <%-- common --%>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/dash.css" />">

    <!-- Latest compiled and minified JavaScript -->
    <script src="<c:url value="/resources/js/jquery/jquery-3.0.0.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery/jquery-ui.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-touchspin.js" />"></script>
    <script src="<c:url value="/resources/js/jquery/jquery.ui.touch-punch.min.js" />"></script>

    <%-- utility libs --%>
    <script src="<c:url value="/resources/js/util/moment.js" />"></script>
    <script src="<c:url value="/resources/js/util/underscore.js" />"></script>
    <script src="<c:url value="/resources/js/util/vector.common.js" />"></script>
    <title>Title</title>
</head>
<body>

