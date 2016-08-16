<%@ include file="header.jsp" %>
<script src="<c:url value="/resources/js/customer.js" />"></script>
<html>
<head>
    <title>Customer Details</title>
</head>
<body>
<div>

    <div class="container">
        <div class="row">
            <div class="span12">
                <ul class="nav nav-tabs" id="customer">
                    <li class="active"><a id="tab-1" href="#view" data-toggle="tab">View</a></li>
                    <li><a href="#add" id="tab-2" data-toggle="tab">Add</a></li>
                    <li><a href="#remove" id="tab-3" data-toggle="tab">Remove</a></li>
                </ul>

                <div class="tab-content">
                    <div class="tab-pane active" id="view">

                    </div>
                    <div class="tab-pane" id="add">
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
                            <%--<div class="form-group">--%>
                            <%--<label for="name">Name</label>--%>
                            <%--<label for="name">Name</label>--%>
                            <%--<input id="name" class="form-control" type="text">--%>
                            <%--</div>--%>
                            <div class="form-group">
                            <div class="button">
                                <button id="submit" class="btn btn-default" type="submit">Submit</button>
                            </div>
                            <div class="button">
                                <button id="cancel" class="btn btn-default" type="submit">Cancel</button>
                            </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane" id="remove">
                        <form role="form">
                            <div class="form-group">
                                <label for="rem_name">Name</label>
                                <input id="rem_name" class="form-control col-sm-10" type="text">
                            </div>
                            <div class="form-group">
                                <div class="button">
                                    <button id="rem_submit" class="btn btn-default" type="Remove">Remove</button>
                                </div>
                                <div class="button">
                                    <button id="rem_cancel" class="btn btn-default" type="submit">Cancel</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
