/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */

VECTOR.namespace("module.customer");

VECTOR.module.customer = function() {
    var idPrefix = "#CUS_";

    var viewCustomers = function(){
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8082/vector/customer',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            success: function(data){
                var x = data;
                $(idPrefix+"#customers").empty();
                _.each(data,function (item) {
                    var row = ' <div class="row dist-file-row" >'
                        + ' <div class="col-lg-1">*</div>'
                        + ' <div class="col-lg-1">hellooooo</div>'
                        + '<div class="col-lg-1"> <button>Edit</button></div>'
                        + 'div class="col-lg-1"> <button>Remove</button></div>'
                        + '</div>';
                    $("#customers").append(row);
                });
                alert(data);
                console.log(data);
            }
        })
    }

    var addCustomer = function(){
        var name=document.getElementById("name").innerHTML;
        var address=document.getElementById("address").innerHTML;
        var telephone=document.getElementById("phone").innerHTML;
        var nic=document.getElementById("NIC").innerHTML;
        var email=document.getElementById("email").innerHTML;
        var array =  [name,address,telephone,nic,email];

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8082/vector/addCustomer',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            success: function(array){
            }
        });
    }
    
    var viewCustomer = function(){
        var id = document.getElementById("nic").innerHTML;
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8082/vector/customerDetail',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            success: function(data){
            }
        });
    }


    return {
        init : function() {
           viewCustomers();
            $("#addCust").off("click").on("click", addCustomer);
            $("#viewCust").off("click").on("click", viewCustomer);
            
        }
    }
}();

$(function() {
    VECTOR.module.customer.init();
});