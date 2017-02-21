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
                        return cid;
                    });
                    $("#customers").append(row);
                    $('#customers li').attr('id', function (cid) {
                        return cid;
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
                        data: {id:id},
                        success: function (data) {
                            $("#customerId").val(data.id);
                            $("#customerName").val(data.custName);
                            $("#customerAddress").val(data.custAddress);
                            $("#customerTelephone").val(data.custTp);
                            $("#customerEmail").val(data.custEmail);
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

        $(idPrefix+"addCustomerBtn").click(function () {
            var details = [$("#addName").val(),$("#addAddress").val(),$("#addPhone").val(),$("#addEmail").val()];
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8082/vector/addCustomer',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: details,
                success: function (data) {
                    console.log(data);
                }
            });
            window.location.reload();
        })

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
            type: 'GET',
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
            // $("#edit").off("click").on("click", viewCustomer);
            $(idPrefix+"removeButton").off("click").on("click", removeCustomer);
            $(idPrefix+"editButton").off("click").on("click", editCustomer);
        }
    }
}();

$(function () {
    VECTOR.module.customer.init();
});