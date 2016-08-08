/**
 * @author MaN
 on 8/8/2016.
 */
VECTOR.namespace("module.samplegraph");

VECTOR.module.samplegraph = function () {
    var idPrefix = "#SG_";
    var crunchifySparkline = function () {
        $.ajax({
            url: 'http://localhost:8082/vector/sparklinetest',
            dataType: "json",
            cache: false,
            contentType: 'application/json; charset=utf-8',
            type: 'GET',
            success: function (result) {
                var one = result.sparkData;
                //alert(one);
                consumeJSONData(one);
            }
        });
    };

    var consumeJSONData=function (sparkData) {
        //console.log(sparkData);
        for (var i = 0; i < sparkData.length; i++) {
            var obj = sparkData[i];
            var crunchifyName;
            var crunchifyValue;
            for (var key in obj) {
                crunchifyName = key;
                crunchifyValue = obj[key].toString();
                crunchifyValue = crunchifyValue.substr(1);
                crunchifyValue = crunchifyValue.substring(0,
                    crunchifyValue.length - 1);
                var n = crunchifyValue.split(",");
               // console.log(n);
                $("#" + crunchifyName).sparkline(n, {
                    type: 'line',
                    width: '160',
                    fillColor: '#eeeeee'
                });
            }
        }
    };

    return {
        init: function () {
            $(".block").css({"background-color": "#7ed233"});
            setInterval(crunchifySparkline, 500);
        }
    }
}();

$(function () {
    VECTOR.module.samplegraph.init();
});