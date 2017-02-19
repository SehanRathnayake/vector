<%--
  Created by IntelliJ IDEA.
  User: Heshani Samarasekara
  Date: 2/19/2017
  Time: 1:16 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<head>
    <title>Customer Details</title>
</head>
<body>
<div class ="col-sm-6 col-md-3" >
    <div class="well-sm">
        <div class="center-block">Customer Details</div>
            <div class="table">
                <div class="table-row-cell">Customer name:${name}</div>
                <div class="table-row-cell">Customer address:${address}</div>
                <div class="table-row-cell">Customer email:${email}</div>
                <div class="table-row-cell">Customer telephone:${telephone}</div>
                <div class="table-row-cell">Customer NIC:${nic}</div>
            </div>
        <div class="well-sm">
            <div class="center-block">Vehicle Details</div>
                <div class="table">
                    <div class="table-row-cell">Vehicle model: ${model}</div>
                    <div class="table-row-cell">Number plate: ${number}</div>
                    <div class="table-row-cell">Manufacture Date: ${manufact}</div>
                    <div class="table-row-cell">Odometer: ${odometer}</div>
                </div>
        </div>
        </div>
</div>
</body>
</html>
