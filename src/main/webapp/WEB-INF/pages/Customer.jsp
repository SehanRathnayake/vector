<html>
<%@ include file="header.jsp" %>
<script src="<c:url value="/resources/js/customer.js"/>"></script>
<head>
    <title>Customer Details</title>
</head>
<div class="vector-header" style="
    font-size: 30px;
    font-family: inherit;
    line-height: 2;
    padding: 8px;
    color: white;
">
    Hi, <security:authentication property="principal.fullname"/>.
    <a href="${logoutUrl}" style="
    float: right;
        font-size: 15px;
    margin-right: 10px;
    color: whitesmoke;
">Log out</a>
</div>
<div>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
            </div>
            <ul class="nav navbar-nav">
                <li><a href="home">Dashboard</a></li>
                <li><a href="customer">Customer</a></li>
                <li><a href="vehicle">Vehicle</a></li>
                <li style="
    float: right;
    background-color: '#47759a';
">
                    <a href="newJob">Create new job</a>
                </li>
            </ul>
        </div>
    </nav>
</div>

<body>
<div>
    <button id="CUS_modelList" type="button" class="btn btn-primary" data-toggle="modal" data-target=".demo-popup">Model List</button>
    <section id="CUS_customerListContainer">
        <div class="container">
            <div class="col-lg-12">
                <div class="col-sm-4 well-sm">
                    <ul id="customers" class="list-group well-lg" >

                    </ul>
                </div>
            </div>
        </div>
        <button id="CUS_addNewCustomer" type="button" class="btn btn-primary" data-toggle="modal" data-target=".demo-popup">Add New Customer</button>

    </section>
    <section id="CUS_customerDetailContainer" style="display: none">
        <div class ="col-sm-6" >
            <div class="panel panel-default" id="CUS_customerDetailPanel">
                <button id="CUS_backToList" class="btn btn-default" type="submit">Back</button>
                <div class="well-sm">
                    <div class="center-block">Customer Details</div>
                    <div class="well">
                        <div>Customer ID:<input id="customerId" class="form-control" disabled/></div>
                        <div>Customer name:<input id="customerName" class="form-control customer-input"></div>
                        <div type="input">Customer address:<input id="customerAddress" class="form-control customer-input"></div>
                        <div type="input">Customer email:<input id="customerEmail" class="form-control customer-input" type="email"></div>
                        <div type="input">Customer telephone:<input id="customerTelephone" class="form-control customer-input"></div>
                    </div>
                </div>
                <div class="well-sm" id="vehicleDiv">
                    <div class="center-block">Vehicle Details</div>
                    <div class="well" id="vehicleDetail">
                        <div class="form-group">
                            <label>Add Vehicle</label>
                        </div>
                        <div class="form-group">
                            <label for="numberPlate1">Number plate</label>
                            <input id="numberPlate1" class="form-control col-sm-10" type="text">
                        </div>
                        <div class="form-group">
                            <label for="manufactDate1">Manufact Date</label>
                            <input id="manufactDate1" class="form-control col-sm-10" type="number" min="1980" max="2017">
                        </div>
                        <div class="form-group">
                            <label for="addModel1">Model</label>
                            <form id="addModel1">
                                <select name="item" class="form-control">
                                <option value="abc">abc</option>
                                <option value="abcd">abcd</option>
                                <option value="abcde">abcde</option>
                                </select>
                            </form>
                        <div class="form-group">
                            <button id="saveVehicle" class="btn btn-default" type="submit">Save</button>
                        </div>
                    </div>
                </div>
                <button id="CUS_removeButton" type="button" class="btn btn-danger btn-sm pull-right">Remove</button>
                <button id="CUS_editButton" type="button" class="btn btn-primary btn-sm pull-right">Edit</button>
            </div>
        </div>
    </section>
    <section id="CUS_addCustomer" style="display: none">
        <div class ="col-sm-10" >
            <div class="well">
                <button id="CUS_backToListButton" class="btn btn-default" type="submit">Back</button>
                <form role="form" id="CUS_addCustomerForm" onsubmit="return false;">
                    <div class="form-group">
                        <label for="addName">Name</label>
                        <input id="addName" class="form-control col-sm-10" type="text">
                    </div>
                    <div class="form-group">
                        <label for="addPhone">Phone</label>
                        <input id="addPhone" class="form-control col-sm-10" type="text">
                    </div>
                    <div class="form-group">
                        <label for="addAddress">Address</label>
                        <input id="addAddress" class="form-control col-sm-10" type="text">
                    </div>
                    <div class="form-group">
                        <label for="addEmail">email</label>
                        <input id="addEmail" class="form-control col-sm-10" type="email">
                    </div>
                </form>
                <form role="form" id="CUS_addVehicleForm" onsubmit="return false;">
                    <div class="form-group"><label>Add Vehicle</label></div>
                    <div class="form-group"><label for="numberPlate11">Number plate</label><input id="numberPlate11" class="form-control col-sm-10" type="text"></div>
                    <div class="form-group"><label for="manufactDate11">Manufact year</label><input id="manufactDate11" class="form-control col-sm-10" type="number" min="1980" max="2017"></div>
                    <div class="form-group"><label for="addModel11">Model</label><form id="addModel11"><select name="item" class="form-control"><option value="abc">abc</option><option value="abcd">abcd</option><option value="abcde">abcde</option></select></div>
                </form>
                <div>
                    <button id="CUS_addMore" class="btn btn-default" type="submit">Add Vehicle</button>'
                </div>
                <button id="CUS_done" class="btn btn-default" type="submit">Done</button>
            </div>
        </div>
    </section>
</div>
</body>
</html>
