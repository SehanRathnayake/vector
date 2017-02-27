/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */

VECTOR.namespace("module.customer");
VECTOR.module.customer = function () {
    var idPrefix = "#CUS_";
    var modelList;
    var select;
    var a;

    var viewCustomerList = function () {
        var vehicle=[];
        var customer;
        var cid;
        $(idPrefix+"editButton").prop("disabled",false);
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8082/vector/customerList',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                $("#customers").empty();
                _.each(data, function (item) {
                    cid = item.cus_id;
                    var row = ' <li class="list-group-item customer-li" id="'+cid+'">'
                        + cid+" "
                        + item.cus_name
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
                            customer = data.customer;
                            vehicle = data.vehicleList;
                            $("#customerId").val(customer.id);
                            $("#customerName").val(customer.custName);
                            $("#customerAddress").val(customer.custAddress);
                            $("#customerTelephone").val(customer.custTp);
                            $("#customerEmail").val(customer.custEmail);
                            $("#vehicleDetail").empty();
                            for(a = 1; a<=vehicle.length;a++){
                                var item = vehicle[a-1];
                                    var row = '<div class="container well vehicle-input-well">'
                                        + '<div class="container well"><div class="row vector-row"><div class="col-lg-3 col-sm-3">Manufacture Date</div><input id="vehicleManufact'+a+'" class="col-lg-3 col-sm-3 vehicle-input" value ="' + item.manufactDate + '" type="number" min="1980" max="2017" disabled></div></div>'
                                        + '<div class="container well"><div class="row vector-row"><div class="col-lg-3 col-sm-3">Number plate</div><input id="vehicleNumber'+a+'" class="col-lg-3 col-sm-3 vehicle-input" value="'+item.numberPlate+'" disabled></div></div>'
                                        + '<div class="container well"><div class="row vector-row"><div class="col-lg-3 col-sm-3">Model</div><input id="vehicleModel'+a+'" class="col-lg-3 col-sm-3 vehicle-input" value="' + item.model + '" disabled></div></div></div>';
                                    $("#vehicleDetail").append(row);
                            }

                        }
                    });
                });
            }
        })
    };

    var addCustomer = function () {
        $(idPrefix + "customerListContainer").hide();
        $(idPrefix + "customerDetailContainer").hide();
        $(idPrefix + "addCustomer").show();
        var idcount = 2;
        var customerId;
        var vehicle = [];
        var customer;
        var vehicleCount = 0;
        var modelList = getModelList();
        $(".vehicle-input").prop("disabled", true);
        $(".customer-input").prop("disabled", true);
        // $(idPrefix+"addCustomerForm").append(select[0].innerHTML);
        
        $(idPrefix + "addMore").click(function () {
            var row1 = '<div class="form-group"><label>Add Vehicle</label></div>'
                +'<div class="container well"><div class="row vector-row"><label class="col-lg-3 col-sm-3" for="numberPlate1'+idcount+'">Number plate</label><input id="numberPlate1'+idcount+'" class="col-lg-3 col-sm-3" type="text" required></div></div>'
                +'<div class="container well"><div class="row vector-row"><label class="col-lg-3 col-sm-3" for="manufactDate1'+idcount+'">Manufact date</label><input id="manufactDate1'+idcount+'" class="col-lg-3 col-sm-3" type="number" min="1980" max="2017" required></div></div>'
                + select
                +'<div class="container well"><div class="row vector-row"><label class="col-lg-3 col-sm-3" for="addModel1'+idcount+'">Model</label><form class="col-lg-3 col-sm-3" id="addModel1'+idcount+'"><select name="item" class="col-lg-3 col-sm-3"><option value=>abc</option><option value="abcd">abcd</option><option value="abcde">abcde</option></select></form></div></div>';
            $(idPrefix + "addVehicleForm").append(row1);
            idcount++;
        });
        $(idPrefix + "done").click(function () {
                /*$(".customer-input").prop("disabled", true);
                $('input').prop("autocomplete", false);
                $(".input").prop("disabled", true);*/
                var disable = false;
                $($(this)).each(function () {
                    if ($(this).val() == "") {
                        disable = true;
                    }
                })
                if (disable) {
                    var vehicleList = [];
                    var data;

                    customer = {
                        custName: "" + $("#addName").val() + "",
                        custEmail: "" + $("#addEmail").val() + "",
                        custTp: $("#addPhone").val(),
                        custAddress: "" + $("#addAddress").val() + "",
                        custNic: ""
                    }

                    var vehicledto;
                    
                    for(var c = 1; c< idcount; c++){
                        vehicledto={
                            vehicleModelId: $("#addModel1" + c).val(),
                            numberPlate: ""+$("#numberPlate1" + c).val()+"",
                            manufactDate: $("#manufactDate1" + c).val(),
                        }
                        vehicleList.push(vehicledto);
                    }
                    
                    data = {
                        customer:customer,
                        vehicleList:vehicleList
                    }
                    
                    
                    $.ajax({
                        type: 'POST',
                        url: 'http://localhost:8082/vector/addCustomer',
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        data: JSON.stringify(data),
                        success: function (data) {
                        }
                    });
                    window.location.reload();
                } else {
                    alert("Please fill all input fields");
                }
            });
    }
    
    var editCustomer = function () {
        var idcount = 1;
        var data;
        var modelList = getModelList();
        $(idPrefix+"customerListContainer").hide();
        $(idPrefix+"customerDetailContainer").show();
        $(idPrefix+"addCustomer").hide();
        $(".customer-input").prop("disabled", false);
        $(idPrefix+"editButton").prop("disabled",true);
        $(idPrefix+"customerDetaiPanel").append(select[0].innerHTML);
        $(idPrefix+"customerDetailPanel").append('<button id="CUS_saveChangesButton" type="button" class="btn btn-primary btn-sm pull-right">Save Changes</button>');
        $("#vehicleDiv").append('<button id="CUS_addMoreInEdit" type="button" class="btn btn-primary btn-sm">Add more vehicles</button>');
        $(".vehicle-input-well").append('<button type="button" class="btn btn-primary btn-sm close">Remove this vehicle</button>');
        $(idPrefix+"addMoreInEdit").click(function () {
            var row1 = '<div class="vehicle-input-well"><label>Add Vehicle</label></div>'
                +'<div class="container well"><div class="row vector-row"><div class="col-lg-3 col-sm-3" for="vehicleNumber'+a+'">Number plate</div><input id="vehicleNumber'+a+'" class="col-lg-3 col-sm-3" type="text" required></div></div>'
                +'<div class="container well"><div class="row vector-row"><div class="col-lg-3 col-sm-3" for="vehicleManufact'+a+'">Manufact year</div><input id="vehicleManufact'+a+'" class="col-lg-3 col-sm-3" type="number" min="1980" max="2017" required></div></div>'
                +'<div class="container well"><div class="row vector-row"><div class="col-lg-3 col-sm-3" for="vehicleModel'+a+'">Model</div><input id="vehicleModel'+a+'" class="col-lg-3 col-sm-3" type="number" min="1980" max="2017" required></div></div>' +
                // +'<button type="button" class="btn btn-primary btn-sm close">Remove this vehicle</button>'
                +'</div>'
                // +select
            $("#vehicleDetail").append(row1);
            a++;
        });
        
        $(idPrefix+"saveChangesButton").click(function(){
            var customer = {
                id: $("#customerId").val(),
                custName : ""+$("#customerName").val()+"",
                custEmail : ""+$("#customerEmail").val()+"",
                custTp : $("#customerTelephone").val(),
                custAddress : ""+$("#customerAddress").val()+"",
                custNic : ""
            }

            var vehicleList = [];

            for(var b = 1; b<= idcount; b++){
                vehicleList.push({
                    vehicleModelId : $("#vehicleModel"+b).val(),
                    numberPlate : $("#vehicleNumber"+b).val(),
                    manufactDate : $("#vehicleManufact"+b).val()
                });
            }
            data = {
                customer:customer,
                vehicleList:vehicleList
            }
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8082/vector/editCustomerDetail',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
                success: function (data) {
                },
            });
            window.location.reload();
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
                alert("The customer successfully removed");
                window.location.reload();
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
    
    var getModelList = function(){
        var models;
        $.ajax({
            type: 'POST',
            url:'http://localhost:8082/vector/modelList',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            async:false,
            success: function(data){
                modelList = data;
                models = data;
                
                var option;
                var list = $(" ");

                for (var b = 0; b<modelList.length;b++) {
                    var val = modelList[b];
                    option = $('<option value="' + val.modelName + '">' + val.modelName+" "+val.manufacturer + '</option>');
                    list.append(option[0].outerHTML);
                }
                select = $('<div class="form-group"><label for="addModel">Model</label><form id="addModel"><select name="item" class="form-control">'+list+'</select></div>');
            }
        });
    }

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
            $(idPrefix+"modelList").off("click").on("click",getModelList);
        }
    }
}();

$(function () {
    VECTOR.module.customer.init();
});