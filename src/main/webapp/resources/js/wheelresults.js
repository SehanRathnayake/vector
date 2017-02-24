/**
 * Created by MaN on 2/19/2017.
 */
VECTOR.namespace("module.wheelresults");

VECTOR.module.wheelresults = function () {
    var idPrefix = "#RS_";
    var customerName;
    var vehicleName;
    var wheel;
    var ip = "localhost";
   // var ip = "192.168.43.150";
    var allResults;
    var basicSeriesList = [];
    var frequencySpectrum;
    var sineWave = [];

    var setSeriesLists = function (deviceId) {
        $.ajax({
            url: 'http://' + ip + ':8082/vector/getChartData',
            dataType: "json",
            data: JSON.stringify(deviceId),
            cache: false,
            contentType: 'application/json;',
            async: false,
            type: 'POST',
            success: function (result) {
                allResults = result;
                var chassisClippedData =  _.map(result.chassiSignalClipped,function (num) {
                   return {x:num[0],y:num[1]}
                });
                var axelClippedData =  _.map(result.axcelSignalClipped,function (num) {
                   return {x:num[0],y:num[1]}
                });
                var differenceData =  _.map(result.differenceSignal,function (num) {
                    return {x:num[0],y:num[1]}
                });
                basicSeriesList=[];
                basicSeriesList.push(chassisClippedData);
                basicSeriesList.push(axelClippedData);
                basicSeriesList.push(differenceData);
                frequencySpectrum=_.map(result.chassiFrequencySpectrum,function (num) {
                    return {x:num[0],y:num[1]}
                });

                var sineOriginal =_.map(result.sinewave,function (num) {
                    return {x:num[0],y:num[1]}
                });
                var sineCreated =_.map(result.sinewave,function (num) {
                    return {x:num[0],y:num[2]}
                });
                sineWave=[];
                sineWave.push(sineOriginal);
                sineWave.push(sineCreated);
                $(idPrefix+"disturbanceTime").html("DisturbanceTime : "+result.disturbanceTime);
                $(idPrefix+"dampingFactor").html("DampingFactor : "+result.dampingFactor);
                $(idPrefix+"dampedFrequency").html("DampedFrequency : "+result.dampedFrequency);
                $(idPrefix+"naturalFrequency").html("NaturalFrequency : "+result.naturalFrequency);
            }
        });
    };

    var setBasicChart = function (deviceId, width, height) {

        customerName = deviceId[0].customerName;
        vehicleName = deviceId[0].vehicleName;
        wheel = deviceId[0].wheelName;
        $(idPrefix + "resultSecTitle").html(customerName + " - " + vehicleName + " " + ":" + wheel);
        var seriesList = [];

        var chartOptions = {
            title: {
                text: "Raw vibrations"
            },
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in IE < IE 10.
                marginRight: 10,
                height: height * 0.5,
                width: width * 0.4,
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
                    return '<b>' + this.series.name + '</b><br/><br/>' +
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
                enabled: true,
                align :'right',
                verticalAlign: 'top',
                layout: 'vertical',
                x: 0,
                y: 10
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Chassis',
                data: basicSeriesList[0],
                turboThreshold: 0,
                visible: true
            }, {
                name: 'Axel',
                data: basicSeriesList[1],
                turboThreshold: 0,
                visible: true
            },
                {
                    name: 'Difference',
                    data: basicSeriesList[2],
                    turboThreshold: 0,
                    visible: false
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
                type: 'column',
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
                text: "Frequency Spectrum"
            },
            colors: ['#64E572',
                '#FF9655', '#FFF263', '#6AF9C4'],
            xAxis: {
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
                enabled: true,
                align : 'right',
                verticalAlign: 'top',
                layout: 'vertical',
                x: 0,
                y: 10
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Chassis',
                data: frequencySpectrum,
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
                text: "Sine wave"
            },
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in IE < IE 10.
                marginRight: 10,
                height: height * 0.5,
                width: width * 0.4,
                events: {
                    load: function () {
                        var chassis = this.series[0];
                        // zSeries.setData(randomDataGen(deviceId, "zAxis"));
                    }
                }
            },
            colors: [ '#DDDF00', '#24CBE5', '#64E572',
                '#FF9655', '#FFF263', '#6AF9C4'],
            xAxis: {
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
                enabled: true,
                align : 'right',
                verticalAlign: 'top',
                layout: 'vertical',
                x: 0,
                y: 10
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Original wave',
                data: sineWave[0],
                visible: true,
                lineWidth: 1
            },{
                name: 'Approximated wave',
                data: sineWave[1],
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
            setSeriesLists(devices);
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