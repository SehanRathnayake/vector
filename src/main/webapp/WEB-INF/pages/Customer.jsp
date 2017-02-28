<html>
<%@ include file="header.jsp" %>
<script src="<c:url value="/resources/js/customer.js"/>"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
<head>
    <title>Customer Details</title>
</head>
<div>
    <nav class="navbar navbar-fixed-top">
        <div class="container-fluid" style="
     background-color: #47759a;
    ">
            <ul class="nav navbar-nav" style="color:whitesmoke" >
                <li><a id="dash-animation" class="dash-background animated shake ">
                    <font style="opacity: 0;">ssadsfs</font> </a></li>
                <li><a href="home" class="color-me" style=" margin-left: 10px">Dashboard</a></li>
                <li><a href="customer" >Customer</a></li>
                <li><a href="newJob">Create New Job</a></li>
            </ul>
            <a href="${logoutUrl}" style="
    float: right;
    font-size: 15px;
    margin-right: 10px;
    color: whitesmoke;
    margin-top: 15px;
    background-position: center;
            ">Log out</a>
        </div>
    </nav>
</div>

<body>
<div>
    <section id="CUS_customerListContainer">
        <div class="container">
            <div class="col-lg-12">
                <div class="well-sm">
                    <ul id="customers" class="list-group well-lg" >

                    </ul>
                </div>
            </div>
        </div>
        <button id="CUS_addNewCustomer" type="button" class="btn btn-primary pull-right">Add New Customer</button>

    </section>
    <section id="CUS_customerDetailContainer" style="display: none">
            <div id="CUS_customerDetailPanel">
                <div>
                    <div class="center-block">Customer Details</div>
                    <div>
                        <div class="container well">
                        <div class="row vector-row"><div class="col-lg-3 col-sm-3"><div>Customer ID</div></div><input id="customerId" class="col-lg-3 col-sm-3" disabled/></div>
                        </div>
                        <div class="container well">
                        <div class="row vector-row"><div class="col-lg-3 col-sm-3"><div>Customer name</div></div><input id="customerName" class="customer-input col-lg-3 col-sm-3"></div>
                            </div>
                        <div class="container well">
                        <div class="row vector-row"><div class="col-lg-3 col-sm-3"><div>Customer address</div></div><input id="customerAddress" class="customer-input col-lg-3 col-sm-3"></div>
                            </div>
                            <div class="container well">
                        <div class="row vector-row"><div class="col-lg-3 col-sm-3"><div>Customer email</div></div><input id="customerEmail" class="customer-input col-lg-3 col-sm-3" type="email"></div>
                                </div>
                        <div class="container well">
                        <div class="row vector-row"><div class="col-lg-3 col-sm-3"><div>Customer telephone</div></div><input id="customerTelephone" class="customer-input col-lg-3 col-sm-3"></div>
                            </div>
                    </div>
                </div>
                <div id="vehicleDiv">
                    <div class="center-block ">Vehicle Details</div>
                    <div id="vehicleDetail">
                        <div class="form-group col-lg-3 col-sm-3">
                            <div>Add Vehicle</div>
                        </div>
                        <div class="container well">
                        <div class="container well">
                            <div class="row vector-row">
                            <div for="numberPlate1" class="col-lg-3 col-sm-3">Number plate</div>
                            <input id="numberPlate1" class="col-lg-3 col-sm-3 customer-input" type="text" disabled>
                                </div>
                        </div>
                        <div class="container well">
                            <div class="row vector-row">
                            <div for="manufactDate1" class="col-lg-3 col-sm-3">Manufact Date</div>
                            <input id="manufactDate1" class="col-lg-3 col-sm-3 customer-input" type="number" min="1980" max="2017" disabled>
                                </div>
                        </div>
                        <div class="container well">
                            <div class="row vector-row">
                            <div for="addModel1" class="col-lg-3 col-sm-3 ">Model</div>
                            <form id="addModel1" class="col-lg-3 col-sm-3 customer-input">
                                <select name="item" class="col-lg-3 col-sm-3" disabled>
                                <option value="abc">abc</option>
                                <option value="abcd">abcd</option>
                                <option value="abcde">abcde</option>
                                </select>
                            </form>
                                </div>
                        </div>
                            </div>
                        <div class="container well">
                            <button id="saveVehicle" class="btn btn-default" type="submit">Save</button>
                        </div>
                    </div>
                </div>
                <button id="CUS_removeButton" type="button" class="btn btn-danger btn-sm pull-right">Remove</button>
                <button id="CUS_editButton" type="button" class="btn btn-primary btn-sm pull-right">Edit</button>
                <button id="CUS_backToList" type="submit" class="btn btn-primary btn-sm pull-right">Back</button>
            </div>
    </section>
    <section id="CUS_addCustomer" style="display: none">
                <form role="form" id="CUS_addCustomerForm" onsubmit="return false;">
                    <div class="container well">
                        <div class="row vector-row">
                        <label for="addName" class="col-lg-3 col-sm-3">Name</label>
                        <input id="addName" class="col-lg-3 col-sm-3" type="text" required>
                            </div>
                    </div>
                    <div class="container well">
                        <div class="row vector-row">
                        <label for="addPhone" class="col-lg-3 col-sm-3">Phone</label>
                        <input id="addPhone" class="col-lg-3 col-sm-3" type="text" required>
                            </div>
                    </div>
                    <div class="container well">
                        <div class="row vector-row">
                        <label for="addAddress" class="col-lg-3 col-sm-3">Address</label>
                        <input id="addAddress" class="col-lg-3 col-sm-3" type="text" required>
                            </div>
                    </div>
                    <div class="container well">
                        <div class="row vector-row">
                        <label for="addEmail" class="col-lg-3 col-sm-3">email</label>
                        <input id="addEmail" class="col-lg-3 col-sm-3" type="email" required>
                            </div>
                    </div>
                </form>
                <form role="form" id="CUS_addVehicleForm" onsubmit="return false;">
                    <div><label>Add Vehicle</label></div>
                    <div class="container well"><div class="row vector-row"><label for="numberPlate11" class="col-lg-3 col-sm-3">Number plate</label><input id="numberPlate11" class="col-lg-3 col-sm-3" type="text" required></div></div>
                    <div class="container well"><div class="row vector-row"><label for="manufactDate11" class="col-lg-3 col-sm-3">Manufact year</label><input id="manufactDate11" class="col-lg-3 col-sm-3" type="number" min="1980" max="2017" required></div></div>
                    <div class="container well"><div class="row vector-row"><label for="addModel11" class="col-lg-3 col-sm-3">Model</label><form id="addModel11"><select name="item" class="col-lg-3 col-sm-3"><option value="abc">abc</option><option value="abcd">abcd</option><option value="abcde">abcde</option></select></form></div></div>
                </form>
                <button id="CUS_addMore" class="btn btn-primary pull-left" type="submit">Add Vehicle</button>'
                <button id="CUS_done" class="btn btn-primary pull-right" type="submit">Done</button>
                <button id="CUS_backToListButton" class="btn btn-primary pull-right" type="submit">Back</button>
    </section>
</div>
</body>
</html>
