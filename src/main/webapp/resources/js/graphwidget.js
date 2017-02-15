/**
 * @author MaN
 on 8/20/2016.
 */
(function ($) {
    var setChartOptions = function (deviceId) {
        var ip="192.168.43.150";
        var chartOptions = {
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in IE < IE 10.
                marginRight: 10,
                height: 245,
                width: 395,
                events: {
                    load: function () {
                        // set up the updating of the chart each second
                        var xSeries = this.series[0];
                        var ySeries = this.series[1];
                        var zSeries = this.series[2];
                        setInterval(function () {
                            xSeries.setData(randomDataGen(deviceId, "xAxis"));
                            ySeries.setData(randomDataGen(deviceId, "yAxis"));
                            zSeries.setData(randomDataGen(deviceId, "zAxis"));
                        }, 1000);
                    }
                }
            },
            colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572',
                '#FF9655', '#FFF263', '#6AF9C4'],
            xAxis: {
                type: 'datetime',
                dateTimeLabelFormats: {
                    second: '%H:%M:%S'
                },
                tickPixelInterval: 10,
                labels: {
                    style: {
                        "font-size": "8px"
                    }
                }
            },
            yAxis: {
                title: {
                    text: null
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#FF0000'
                }]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            plotOptions: {
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
            },
            legend: {
                layout: 'horizontal',
                style: {
                    "font-size": "8px"
                },
                borderWidth: 0,
                enabled: true
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'X Axis',
                data: (function () {
                    // generate an array of random data
                    var data = [], time = (new Date()).getTime(), i;
                    for (i = -100; i <= 0; i += 1) {
                        data.push({
                            x: time + i * 1000,
                            y: Math.random()
                        });
                    }
                    return data;
                }())
            }, {
                name: 'Y Axis',
                data: (function () {
                    // generate an array of random data
                    var data = [], time = (new Date()).getTime(), i;
                    for (i = -100; i <= 0; i += 1) {
                        data.push({
                            x: time + i * 1000,
                            y: Math.random()
                        });
                    }
                    return data;
                }()),
                visible: false
            }, {
                name: 'Z Axis',
                data: (function () {
                    // generate an array of random data
                    var data = [], time = (new Date()).getTime(), i;
                    for (i = -100; i <= 0; i += 1) {
                        data.push({
                            x: time + i * 1000,
                            y: Math.random()
                        });
                    }
                    return data;
                }()),
                visible: false
            }],
            global: {
                useUTC: false
            }

        };
        return chartOptions;
    };


    var randomDataGen = function (deviceId, axis) {
        var data = [];
        $.ajax({
            url: 'http://'+ip+':8082/vector/graph',
            dataType: "json",
            cache: false,
            data: deviceId,
            contentType: 'application/json;',
            type: 'POST',
            async: false,
            success: function (result) {
                var time = (new Date()).getTime(), i;
                for (i = -100; i < 0; i += 1) {
                    var rAxis;
                    switch (axis) {
                        case "xAxis":
                            var previousValue = result.xAxis[0];
                            rAxis = result.xAxis[100 + i];
                            if (rAxis >previousValue+5000 || rAxis<previousValue-5000) {
                               rAxis = (previousValue + result.xAxis[100 + 1 + i]) / 2;

                            }
                            if (_.isEqual(rAxis, -1)) {
                                rAxis = (result.xAxis[100 + i + 1] + result.xAxis[100 + i + 1]) / 2
                            }
                            previousValue = rAxis;
                            break;
                        case "yAxis":
                            var previousValueY =  result.yAxis[0];
                            rAxis = result.yAxis[100 + i];
                            if (rAxis >previousValueY+5000 || rAxis<previousValueY-5000) {
                                rAxis = (previousValueY + result.yAxis[100 + 1 + i]) / 2;

                            }
                            if (_.isEqual(rAxis, -1)) {
                                rAxis = (result.yAxis[100 + i + 1] + result.yAxis[100 + i + 1]) / 2
                            }
                            previousValueY = rAxis;
                            break;
                        case "zAxis":
                            var previousValueZ =  result.zAxis[0];
                            rAxis = result.zAxis[100 + i];
                            if (_.isEqual(rAxis, -1)) {
                                rAxis = (result.zAxis[100 + i + 1] + result.zAxis[100 + i + 1]) / 2
                            }
                            if (rAxis >previousValueZ+5000 || rAxis<previousValueZ-5000) {
                                rAxis = (previousValueZ + result.zAxis[100 + 1 + i]) / 2;

                            }
                            previousValueZ = rAxis;

                            break;
                    }

                    data.push({
                        x: time + i * 10,
                        y: rAxis
                    });
                }
            }
        });
        return data;
    };
    var generateSampleDataBuffer = function () {
        $.ajax({
            url: 'http://'+ip+':8082/vector/rest/test',
            dataType: "json",
            cache: false,
            data: deviceId,
            contentType: 'application/json;',
            type: 'POST',
            success: function (result) {
                var x = result;
            }
        });//your code to be executed after 1 second
    };
    $.fn.vectorDashboardGraph = function (job, device) {
        var chartOptions = setChartOptions(device);
        chartOptions["title"] = {
            text: job + ":" + device
        };
        this.highcharts(chartOptions);
        Highcharts.setOptions(Highcharts.theme);
        console.log("yayyy !!!!");
        //setInterval(generateSampleDataBuffer,1000);
        return this;
    };

}(jQuery));