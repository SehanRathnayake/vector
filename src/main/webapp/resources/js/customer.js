/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */

VECTOR.namespace("module.customer");
VECTOR.module.customer = function () {
    var idPrefix = "#CUS_";

    var viewCustomerList = function () {
        var vehicle;
        var customer;
        var cid;
        $(idPrefix+"editButton").prop("disabled",false);
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8082/vector/customerList',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                $("#customers").empty();
                _.each(data, function (item) {
                    cid = item.customerid;
                    var row = ' <li class="list-group-item customer-li" id="'+cid+'">'
                        + cid+" "
                        + item.customerName
                        + '</li>';
                    $("#customers").append(row);
                });
                $(".customer-li").off("click").on("click", function(){
                    $(idPrefix+"customerListContainer").hide();
                    $(idPrefix+"customerDetailContainer").show();
                    $(idPrefix+"addCustomer").hide();
                    $(".vehicle-input").prop("disabled", true);
                    $(".customer-input").prop("disabled", true);
                    var id = $(this).attr("id");
                    $.ajax({
                        type: 'POST',
                        url: 'http://localhost:8082/vector/viewCustomerDetail',
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        data: id,
                        success: function (data) {
                            customer = data[0];
                            vehicle = data[1];
                            $("#customerId").val(customer.id);
                            $("#customerName").val(customer.custName);
                            $("#customerAddress").val(customer.custAddress);
                            $("#customerTelephone").val(customer.custTp);
                            $("#customerEmail").val(customer.custEmail);
                            $("#vehicleDetail").empty();
                            var i = 0;
                            _.each(data, function (item) {
                                if(i!=0) {
                                    var row = '<div type="input">Vehicle model:<input id="vehicleModel" class="form-control vehicle-input" value ="' + item.vehicleModelId + '"></div>'
                                        + '<div type="input">Manufacture Date: <input id="vehicleManufact" class="form-control vehicle-input" value ="' + item.manufactDate + '"></div>'
                                        + '<div type="input">Number plate: <input id="vehicleNumber" class="form-control vehicle-input" value="'+item.numberPlate+'"></div>'
                                        + '<div type="input">Odometer: <input id="vehicleOdo" class="form-control vehicle-input" value="' + item.odometer + '"></div>'
                                        + '<div type="input">Model: <input id="vehicleModel" class="form-control vehicle-input" value="' + item.model + '"></div>';
                                    $("#vehicleDetail").append(row);
                                }
                                i++;
                            });

                        }
                    });
                });
            }
        })
    };

    var addCustomer = function () {
        $(idPrefix+"customerListContainer").hide();
        $(idPrefix+"customerDetailContainer").hide();
        $(idPrefix+"addCustomer").show();
        var count = 1;
        var customerId;
        var vehicle ;
        var customer;
        var vehicleCount = 0;
        $(".vehicle-input").prop("disabled", true);
        $(idPrefix+"addMore").click(function () {
            // var row = '<div type="input">Vehicle model:<input id="vehicleModel" class="form-control vehicle-input" value =""></div>'
            //     + '<div type="input">Manufacture Date: <input id="vehicleManufact" class="form-control vehicle-input" value =></div>'
            //     + '<div type="input">Number plate: <input id="vehicleNumber" class="form-control vehicle-input" value=""></div>'
            //     + '<div type="input">Odometer: <input id="vehicleOdometer" class="form-control vehicle-input" value=""></div>'
            //     + '<button id="saveVehicle" class="btn btn-default" type="submit">Save</button>';


            var row1 = '<div class="form-group"><label>Add Vehicle</label></div>'
                +'<div class="form-group"><label for="numberPlate">Number plate</label><input id="numberPlate" class="form-control col-sm-10" type="text"></div>'
                +'<div class="form-group"><label for="manufactDate">Number plate</label><input id="manufactDate" class="form-control col-sm-10" type="date"></div>'
                +'<div class="form-group"><label for="addModel">Model</label><form id="addModel"><select name="item" class="form-control"><option value="abc">abc</option><option value="abcd">abcd</option><option value="abcde">abcde</option></select></div>'
                +'<div class="form-group"><label for="addOdometer">Odometer</label><input id="addOdometer" class="form-control col-sm-10" type="number"></div>'
                +'<div class="form-group"><button id="saveVehicle" class="btn btn-default" type="submit">Save</button></div>';
            $(idPrefix+"addVehicleForm").append(row1);
        });

        $(idPrefix+"addCustomerBtn").click(function () {
            $(".customer-input").prop("disabled", false);
            var disable = false;
            $($(this)).each(function () {
                if ($(this).val() == "") {
                    disable = true;
                }
            });
            if (!disable) {
                alert("Please fill all input fields")
            } else {
                customer = {
                    "custName" : ""+$("#addName").val()+"",
                    "custEmail" : ""+$("#addEmail").val()+"",
                    "custPhone" : ""+$("#addPhone").val()+"",
                    "custAddress" : ""+$("#addAddress").val()+""
                }
            }
        });

        $("#saveVehicle").click(function () {
            vehicleCount++;
            if (ifAllFilled(".vehicle-input")) {
                alert("Please fill all input fields")
            } else {
                vehicle[vehicleCount] = {
                    customerId : customerId,
                    vehModel : $("#vehicleModel").val(),
                    vehManufact : $("#vehicleManufact").val(),
                    vehNumber : $("#vehicleNumber").val(),
                    vehOdometer : $("#vehicleOdometer").val()
                }
            }
        });

        $(idPrefix+"done").click(function() {
            $(".input").prop("disabled", false);
            var disable = false;
            $($(this)).each(function () {
                if ($(this).val() == "") {
                    disable = true;
                }
            })
            if (disable) {
                var returnData = customer;
                $.ajax({
                    type: 'POST',
                    url: 'http://localhost:8082/vector/addCustomer',
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(returnData),
                    success: function (data) {
                        // console.log(data);
                        customerId = data;
                    }
                });
                // window.location.reload();
            } else {
                alert("Please fill all input fields");
            }
        });
    };

    var editCustomer = function () {
        $(idPrefix+"customerListContainer").hide();
        $(idPrefix+"customerDetailContainer").show();
        $(idPrefix+"addCustomer").hide();
        $(".customer-input").prop("disabled", false);
        $(".vehicle-input").prop("disabled", false);
        $(idPrefix+"editButton").prop("disabled",true);
        $(idPrefix+"customerDetailPanel").append('<button id="CUS_saveChangesButton" type="button" class="btn btn-primary btn-sm pull-right">Save Changes</button>');
        $("#vehicleDetail").append('<button id="CUS_addMoreInEdit" type="button" class="btn btn-primary btn-sm pull-right">+</button>');
        $(idPrefix+"addMoreInEdit").click(function () {
            var row1 = '<div class="form-group"><label>Add Vehicle</label></div>'
                +'<div class="form-group"><label for="numberPlate">Number plate</label><input id="numberPlate" class="form-control col-sm-10" type="text"></div>'
                +'<div class="form-group"><label for="manufactDate">Number plate</label><input id="manufactDate" class="form-control col-sm-10" type="date"></div>'
                +'<div class="form-group"><label for="addModel">Model</label><form id="addModel"><select name="item" class="form-control"><option value="abc">abc</option><option value="abcd">abcd</option><option value="abcde">abcde</option></select></div>'
                +'<div class="form-group"><label for="addOdometer">Odometer</label><input id="addOdometer" class="form-control col-sm-10" type="number"></div>'
                +'<div class="form-group"><button id="saveVehicle" class="btn btn-default" type="submit">Save</button></div>';
            $("#vehicleDetail").append(row1);
        });
        var details = [$("#customerId").val(),$("#customerName").val(),$("#customerAddress").val(),$("#customerTelephone").val(),$("#customerEmail").val()];
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8082/vector/editCustomerDetail',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: details,
            success: function (data) {
            },
        });
    };

    var removeCustomer = function () {
        var removeId = $("#customerId").val();
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8082/vector/removeCustomer',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data : removeId,
            success: function (data) {
            }
        });
    };

    var saveChanges = function(){
        $(".customer-input").prop("disabled", true);
        $(".vehicle-input").prop("disabled", true);
    };

    var ifAllFilled = function(attr){
        $(attr).prop("disabled", false);
        var disable = false;
        $($(this)).each(function () {
            if ($(this).val() == "") {
                disable = true;
            }
        });
        return disable;
    };

    return {
        init: function () {
            viewCustomerList();
            $(idPrefix+"addNewCustomer").off("click").on("click", addCustomer);
            $(idPrefix+"removeButton").off("click").on("click", removeCustomer);
            $(idPrefix+"editButton").off("click").on("click", editCustomer);
            $(idPrefix+"cancelAdd").off("click").on("click", viewCustomerList);
            $(idPrefix+"backToListButton").off("click").on("click",function(){
                window.location.reload();
            });
            $(idPrefix+"backToList").off("click").on("click",function(){
                window.location.reload();
            });
            $(idPrefix+"saveChangesButton").off("click").on("click",viewCustomerList);
        }
    }
}();

$(function () {
    VECTOR.module.customer.init();
});