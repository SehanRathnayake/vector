/**
    * @author MaN
    *      on 6/28/2016.
    */
VECTOR.namespace("module.home");

VECTOR.module.home = function() {
    var idPrefix = "#H_";
    var buttonClick = function () {
        if($("h1").css("color")=='rgb(255, 0, 0)'){
            $("h1").css({"color":"black"});
        }else{
            $("h1").css({"color":"red"}); 
        }
    };
    return {
        init : function() {
           $(idPrefix+"testBtn").off('click').on('click',buttonClick);
        }
    }
}();

$(function() {
    VECTOR.module.home.init();
});