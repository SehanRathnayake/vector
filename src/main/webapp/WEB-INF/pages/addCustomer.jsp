<%--
  Created by IntelliJ IDEA.
  User: Heshani Samarasekara
  Date: 2/18/2017
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@ include file="header.jsp" %>
    <script src="<c:url value="/resources/js/customer.js"/>"></script>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Add New Customer</title>
</head>
<body>
<form role="form">
    <div class="form-group">
        <label for="name">Name</label>
        <input id="name" class="form-control col-sm-10" type="text">
    </div>
    <div class="form-group">
        <label for="NIC">NIC</label>
        <input id="NIC" class="form-control col-sm-10" type="number">
    </div>
    <div class="form-group">
        <label for="phone">Phone</label>
        <input id="phone" class="form-control col-sm-10" type="tel">
    </div>
    <div class="form-group">
        <label for="address">Address</label>
        <input id="address" class="form-control col-sm-10" type="text">
    </div>
    <div class="form-group">
        <label for="email">email</label>
        <input id="email" class="form-control col-sm-10" type="email">
    </div>
    <div class="form-group">
        <label for="brand">Brand</label>
        <form action="customer.jsp" id="brand">
            <select name="item">
                <option value="Honda">Honda</option>
                <option value="Suzuki">Suzuki</option>
                <option value="kjdsa">kjdsa</option>
            </select>
            <input type="submit" value="Submit">
        </form>
    </div>
    <div class="form-group">
        <label for="model">email</label>
        <form action="customer.jsp" id="model">
            <select name="item">
                <option value="Honda">Honda</option>
                <option value="Suzuki">Suzuki</option>
                <option value="kjdsa">kjdsa</option>
            </select>
            <input type="submit" value="Submit">
        </form>
    </div>
    <div class="form-group">
        <label for="odometer">email</label>
        <input id="odometer" class="form-control col-sm-10" type="odometer">
    </div>
    <div class="form-group">
        <div class="button">
            <button id="addCust" class="btn btn-default" type="submit">Submit</button>
        </div>
        <div class="button">
            <button id="cancel" class="btn btn-default" type="submit">Cancel</button>
        </div>
    </div>
</form>
</body>
</html>
