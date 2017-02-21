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
<script src="<c:url value="/resources/js/customer.js"/>"></script>
<head>
    <title>Customer Details</title>
</head>

<div>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
            </div>
            <ul class="nav navbar-nav">
                <li><a href="home">Dashboard</a></li>
                <li class="active" style="float: right; background-color: '#47759a'"><a href="customer">Customer</a></li>
                <li><a href="vehicle">Vehicle</a></li>
                <li><a href="new">Create new job</a></li>
            </ul>
        </div>
    </nav>
</div>

<body>

<div class ="col-sm-4" >
    <div class="well-sm">
        <div class="center-block">Customer Details</div>
        <div class="well">
            <div class="row-sm-4">Customer name:${name}</div>
            <div class="row-sm-4">Customer address:${address}</div>
            <div class="row-sm-4">Customer email:${email}</div>
            <div class="row-sm-4">Customer telephone:${telephone}</div>
            <div class="row-sm-4">Customer NIC:${nic}</div>
        </div>
    </div>
    <div class="well-sm">
        <div class="center-block">Vehicle Details</div>
        <div class="well">
            <div class="row-sm-4">Vehicle model: ${model}</div>
            <div class="row-sm-4">Number plate: ${number}</div>
            <div class="row-sm-4">Manufacture Date: ${manufact}</div>
            <div class="row-sm-4">Odometer: ${odometer}</div>
        </div>
    </div>
    <button id="remove" type="button" class="btn btn-danger btn-sm pull-right">Remove</button>
    <button id="edit" type="button" class="btn btn-primary btn-sm pull-right" data-toggle="modal" data-target=".demo-popup">Edit</button>
</div>

<div class="modal fade demo-popup" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title">Edit User Details</h3>
            </div>
            <div class="modal-body">
                <P><form role="form">
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
                    <form id="brand">
                        <select name="item">
                            <option value="Honda">Honda</option>
                            <option value="Suzuki">Suzuki</option>
                            <option value="kjdsa">kjdsa</option>
                        </select>
                    </form>
                </div>
                <div class="form-group">
                    <label for="model">email</label>
                    <form id="model">
                        <select name="item">
                            <option value="Honda">Honda</option>
                            <option value="Suzuki">Suzuki</option>
                            <option value="kjdsa">kjdsa</option>
                        </select>
                    </form>
                </div>
                <div class="form-group">
                    <label for="odometer">email</label>
                    <input id="odometer" class="form-control col-sm-10" type="odometer">
                </div>
                <div class="form-group">
                    <div class="button">
                        <button id="addCust" class="btn btn-default" type="submit">Save</button>
                    </div>
                    <div class="button">
                        <button id="cancel" class="btn btn-default" type="submit">Cancel</button>
                    </div>
                </div>
            </form></P>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
