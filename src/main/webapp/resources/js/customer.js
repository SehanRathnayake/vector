/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */

VECTOR.namespace("module.customer");

VECTOR.module.customer = function() {
    var idPrefix = "#CUS_";
    $("a[data-tab-destination]").on('click', function() {
        var tab = $(this).attr('data-tab-destination');
        $("#"+tab).click();
    });
    return {
        init : function() {
        }
    }
}();

$(function() {
    VECTOR.module.customer.init();
});