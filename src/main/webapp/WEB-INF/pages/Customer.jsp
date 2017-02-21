<html>
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
                <li class="active" style="float: right; background-color: '#47759a'"><a href="#">Customer</a></li>
                <li><a href="vehicle">Vehicle</a></li>
                <li><a href="new">Create new job</a></li>
            </ul>
        </div>
    </nav>
</div>

<body>

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

    <%--<div class="modal fade demo-popup" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title">Add new customer</h3>
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
                        <label for="model">Model</label>
                        <form id="model">
                            <select name="item">
                                <option value="Honda">Honda</option>
                                <option value="Suzuki">Suzuki</option>
                                <option value="kjdsa">kjdsa</option>
                            </select>
                        </form>
                    </div>
                    <div class="form-group">
                        <label for="odometer">Odometer</label>
                        <input id="odometer" class="form-control col-sm-10" type="odometer">
                    </div>
                    <div class="form-group">
                        <button id="addCustomer" class="btn btn-default" data-dismiss="modal" aria-hidden="true" type="submit">Save</button>
                        <button id="cancel" class="btn btn-default" data-dismiss="modal" aria-hidden="true" type="submit">Cancel</button>
                    </div>
                </form></P>
                </div>
            </div>
        </div>
    </div>--%>
</section>
<section id="CUS_customerDetailContainer" style="display: none">
    <div class ="col-sm-6" >
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
        <div class="well-sm">
            <div class="center-block">Vehicle Details</div>
            <div class="well" id="vehicleDetail">

            </div>
        </div>
        <button id="CUS_removeButton" type="button" class="btn btn-danger btn-sm pull-right">Remove</button>
        <button id="CUS_editButton" type="button" class="btn btn-primary btn-sm pull-right">Edit</button>
    </div>
    <%--<div class="modal fade demo-popup" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title">Edit User Details</h3>
                </div>
                <div class="modal-body">
                    <P></P>
                </div>
            </div>
        </div>
    </div>--%>
</section>

</body>
<section id="CUS_addCustomer" style="display: none">
    <div class ="col-sm-10" >
        <div class="well">
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
        <div class="form-group">
            <button id="CUS_addCustomerBtn" class="btn btn-default" type="submit">Save</button>
        </div>
    </form>
            <form role="form" id="CUS_addVehicleForm">
        <div class="form-group">
            <label>Add Vehicle</label>
        </div>
        <div class="form-group">
            <label for="numberPlate">Number plate</label>
            <input id="numberPlate" class="form-control col-sm-10" type="text">
        </div>
        <div class="form-group">
            <label for="manufactDate">Number plate</label>
            <input id="manufactDate" class="form-control col-sm-10" type="date">
        </div>
        <div class="form-group">
            <label for="addModel">Model</label>
            <form id="addModel">
                <select name="item" class="form-control">
                    <option value="abc">abc</option>
                    <option value="abcd">abcd</option>
                    <option value="abcde">abcde</option>
                </select>
            </form>
        </div>
        <div class="form-group">
            <label for="addOdometer">Odometer</label>
            <input id="addOdometer" class="form-control col-sm-10" type="number">
        </div>
        <div class="form-group">
            <button id="CUS_addMore" class="btn btn-default alert-info" type="submit">+</button>
        </div>
                <button id="CUS_addVehicleBtn" class="btn btn-default" type="submit">Save</button>
</form>
            <div>

            </div>
            <button id="CUS_done" class="btn btn-default" type="submit">Done</button>
            </div>
        </div>
</section>
</html>
