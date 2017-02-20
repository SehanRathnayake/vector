/**
 * Created by MaN on 2/19/2017.
 */
VECTOR.namespace("module.wheelresults");

VECTOR.module.wheelresults = function () {
    var idPrefix = "#RS_";
    var customerName;
    var vehicleName;
    var wheel;
    var setBasicChart = function (deviceId, width, height) {
        var ip = "localhost";
        //var ip = "192.168.150.43";
        customerName=deviceId[0].customerName;
        vehicleName=deviceId[0].vehicleName;
        wheel=deviceId[0].wheelName;
        $(idPrefix+"resultSecTitle").html(customerName+" - "+vehicleName+" "+":"+wheel);
        var seriesList = [];
        $.ajax({
            url: 'http://' + ip + ':8082/vector/getRawData',
            dataType: "json",
            data: JSON.stringify(deviceId),
            cache: false,
            contentType: 'application/json;',
            async: false,
            type: 'POST',
            success: function (result) {
                _.each(result, function (item) {
                    var series = [];
                    _.each(item.vibration, function (vdata, key) {
                        if (true) {
                            var data = {
                                x: item.time[key],
                                y: vdata
                            };
                            series.push(data);
                        }
                    });
                    seriesList.push(series);
                })
            }
        });
        var chartOptions = {
            title: {
                text: "Raw vibrations"
            },
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in IE < IE 10.
                marginRight: 10,
                height: height * 0.5,
                width: width * 0.95,
                events: {
                    load: function () {
                        var chassis = this.series[0];
                        var axel = this.series[1];
                        // zSeries.setData(randomDataGen(deviceId, "zAxis"));
                    }
                }
            },
            colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572',
                '#FF9655', '#FFF263', '#6AF9C4'],
            xAxis: {
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
                name: 'Chassis',
                data: seriesList[0],
                turboThreshold:0,
                visible: true
            }, {
                name: 'Axel',
                data: seriesList[1],
                turboThreshold:0,
                visible: true
            }],
            global: {
                useUTC: false
            }

        };
        return chartOptions;
    };
    var setDampChart = function (deviceId, width, height) {
        var ip = "localhost";
        //var ip = "192.168.150.43";
        var dampingChartOptions = {
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in IE < IE 10.
                marginRight: 10,
                height: height * 0.4,
                width: width * 0.4,
                events: {
                    load: function () {
                        var chassis = this.series[0];
                        // zSeries.setData(randomDataGen(deviceId, "zAxis"));
                    }
                }
            },

            title: {
                text: "Raw vibrations"
            },
            colors: ['#64E572',
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
                name: 'Chassis',
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
                visible: true
            }],
            global: {
                useUTC: false
            }

        };
        return dampingChartOptions;
    };
    var setFourierChart = function (deviceId, width, height) {
        var ip = "localhost";
        //var ip = "192.168.150.43";
        var fourierChartOptions = {
            title: {
                text: "Frequency Domain"
            },
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in IE < IE 10.
                marginRight: 10,
                height: height * 0.4,
                width: width * 0.4,
                events: {
                    load: function () {
                        var chassis = this.series[0];
                        // zSeries.setData(randomDataGen(deviceId, "zAxis"));
                    }
                }
            },
            colors: ['#ED561B', '#DDDF00', '#24CBE5', '#64E572',
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
                name: 'Chassis',
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
                visible: true
            }],
            global: {
                useUTC: false
            }

        };
        return fourierChartOptions;
    };


    return {
        init: function () {
        },
        getCharts: function (devices) {
            var chartOptions = setBasicChart(devices, $(idPrefix + "resultSec").width(), $(idPrefix + "resultSec").height());
            $(idPrefix + "basicResultChart").highcharts(chartOptions);
            var dChartOptions = setDampChart(1, $(idPrefix + "resultSec").width(), $(idPrefix + "resultSec").height());
            $(idPrefix + "dampingChart").highcharts(dChartOptions);
            var fChartOptions = setFourierChart(1, $(idPrefix + "resultSec").width(), $(idPrefix + "resultSec").height());
            $(idPrefix + "fourierChart").highcharts(fChartOptions);
            Highcharts.setOptions(Highcharts.theme);
            $(idPrefix + "resultSecClose").off("click").on("click", function () {
                $(idPrefix + "resultSec").hide();
            })
        }
    }
}();

$(function () {
    VECTOR.module.wheelresults.init()
});