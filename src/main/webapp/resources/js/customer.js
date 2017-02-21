/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */

VECTOR.namespace("module.customer");
VECTOR.module.customer = function () {
    var idPrefix = "#CUS_";

    var viewCustomerList = function () {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8082/vector/customerList',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                var x = data;
                $("#customers").empty();
                _.each(data, function (item) {
                    var cid = item.customerid;
                    var row = ' <li class="list-group-item customer-li">'
                        + cid+" "
                        + item.customerName
                        + '</li>';
                    $('#customers li').attr('id', function (cid) {
                        return cid+1;
                    });
                    $("#customers").append(row);
                    $('#customers li').attr('id', function (cid) {
                        return cid+1;
                    });
                });
                $(".customer-li").off("click").on("click", function(){
                    $(idPrefix+"customerListContainer").hide();
                    $(idPrefix+"customerDetailContainer").show();
                    $(idPrefix+"addCustomer").hide();
                    $(document).ready(function () {
                        $('.form-popup').modal({show: true})
                    });
                    var id = $(this).attr("id");
                    $(".customer-input").prop("disabled", true);
                    $.ajax({
                        type: 'POST',
                        url: 'http://localhost:8082/vector/viewCustomerDetail',
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        data: id,
                        success: function (data) {
                            var customer = data[0];
                            var vehicle = data[1];
                            $("#customerId").val(customer.id);
                            $("#customerName").val(customer.custName);
                            $("#customerAddress").val(customer.custAddress);
                            $("#customerTelephone").val(customer.custTp);
                            $("#customerEmail").val(customer.custEmail);
                            $("#vehicleDetail").empty();
                            var i = 0;
                            _.each(data, function (item) {
                                if(i!=0) {
                                    var row = '<div class="well" id="vehicleDetail">'
                                        + '<div type="input">Vehicle model:<input id="vehicleModel" class="form-control customer-input" value ="' + item.vehicleModelId + '"></div>'
                                        + '<div type="input">Manufacture Date: <input id="vehicleManufact" class="form-control customer-input" value ="' + item.manufactDate + '"></div>'
                                        + '<div type="input">Number plate: <input id="vehicleNumber" class="form-control customer-input" value="'+item.numberPlate+'"></div>'
                                        + '<div type="input">Odometer: <input id="vehicleOdo" class="form-control customer-input" value="' + item.odometer + '"></div>'
                                        + '<div type="input">Model: <input id="vehicleModel" class="form-control customer-input" value="' + item.model + '"></div>'
                                        + '</div>';
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
        var vehicleCount = 0;
        $(".vehicle-input").prop("disabled", true);
        $(idPrefix+"addMore").click(function () {
            var row = '<div type="input">Vehicle model:<input id="vehicleModel" class="form-control vehicle-input" value =""></div>'
                + '<div type="input">Manufacture Date: <input id="vehicleManufact" class="form-control vehicle-input" value =></div>'
                + '<div type="input">Number plate: <input id="vehicleNumber" class="form-control vehicle-input" value=""></div>'
                + '<div type="input">Odometer: <input id="vehicleOdometer" class="form-control vehicle-input" value=""></div>'
                + '<button id="saveVehicle" class="btn btn-default" type="submit">Save</button>'
                + '<button id="CUS_addVehicleBtn" class="btn btn-default" type="submit">Save</button>';
            $(idPrefix+"addCustomerForm").append(row);
        });

        $(idPrefix+"addCustomerBtn").click(function () {
            $(".vehicle-input").prop("disabled", false);
                    var disable = false;
                    $('input:text').each(function () {
                        if ($(this).val() == "") {
                            disable = true;
                        }
                    });
                    if (disable) {
                        alert("Please fill all input fields")
                    } else {
                        var customer = {
                            custName : $("#addName").val(),
                            custEmail : $("#addEmail").val(),
                            custPhone : $("#addPhone").val(),
                            custAddress : $("#addAddress").val()
                        }
                    }
            });

        $(idPrefix+"addVehicleBtn").click(function () {
            vehicleCount++;
            $(".vehicle-input").prop("disabled", false);
            var disable = false;
            $('input:text').each(function () {
                if ($(this).val() == "") {
                    disable = true;
                }
            });
            if (disable) {
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

        $(idPrefix+"done").click(function () {
            var returnData = customer.concat(vehicle);
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8082/vector/addCustomer',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: returnData,
                success: function (data) {
                    // console.log(data);
                    customerId = data;
                }
            });
            window.location.reload();
        });
    };

    var editCustomer = function () {
        $(idPrefix+"customerListContainer").hide();
        $(idPrefix+"customerDetailContainer").show();
        $(idPrefix+"addCustomer").hide();
        $(".customer-input").prop("disabled", false);
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

    return {
        init: function () {
            viewCustomerList();
            $(idPrefix+"addNewCustomer").off("click").on("click", addCustomer);
            $(idPrefix+"removeButton").off("click").on("click", removeCustomer);
            $(idPrefix+"editButton").off("click").on("click", editCustomer);
            $(idPrefix+"cancelAdd").off("click").on("click", viewCustomerList());
        }
    }
}();

$(function () {
    VECTOR.module.customer.init();
});