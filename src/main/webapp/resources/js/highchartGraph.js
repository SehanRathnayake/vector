/**
 * Created by Heshani Samarasekara on 8/10/2016.
 */

VECTOR.namespace("module.highchartGraph");

VECTOR.module.highchartGraph = function() {
    var idPrefix = "#HCG_";
    var prevData = randomDataGen();
    var showData = [];

    var chart = {
        type: 'spline',
        animation: Highcharts.svg, // don't animate in IE < IE 10.
        marginRight: 10,
        height:195,
        width:295,
        events: {
            load: function () {
                // set up the updating of the chart each second
                var series = this.series[0];
                setInterval(function () {
                    for(var i = 0; i < 90; i++){
                        showdata[i] = prevData[i+9];
                    }
                    series.setData(randomDataGen())
                }, 1000);
            }
        }
    };
    var title = {
        text: 'Data'
    };
    var xAxis = {
        type: 'datetime',
        tickPixelInterval: 10
    };
    var yAxis = {
        title: {
            text: 'Value'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#FF0000'
        }]
    };
    var tooltip = {
        formatter: function () {
            return '<b>' + this.series.name + '</b><br/>' +
                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                Highcharts.numberFormat(this.y, 2);
        }
    };
    var plotOptions = {
        area: {
            pointStart: 19,
            marker: {
                enabled: false,
                symbol: 'circle',
                radius: 2,
                states: {
                    hover: {
                        enabled: false
                    }
                }
            }
        }
    };
    var legend = {
        enabled: false
    };
    var exporting = {
        enabled: false
    };
    var series= [{
        name: 'Random data',
        data: (function () {
            // generate an array of random data
            var data = [],time = (new Date()).getTime(),i;
            for (i = -100; i <= 0; i += 1) {
                data.push({
                    x: time + i * 1000,
                    y: Math.random()
                });
            }
            return data;
        }())
    }];

    var json = {};
    json.chart = chart;
    json.title = title;
    json.tooltip = tooltip;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    json.exporting = exporting;
    json.series = series;
    json.plotOptions = plotOptions;


    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    $(idPrefix+'container').highcharts(json);

    var randomDataGen = function () {
        var data = [],time = (new Date()).getTime(),i;
        for (i = -100; i <= 0; i += 1) {
            data.push({
                x: time + i * 1000,
                y: Math.random()
            });
        }
        return data;
    };
return {
        init : function() {

        }
    }
}();

$(function() {
    VECTOR.module.highchartGraph.init();
});