/**
 * @author MaN
 on 8/20/2016.
 */
(function ($) {
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
                        xSeries.setData(randomDataGen());
                        ySeries.setData(randomDataGen());
                        zSeries.setData(randomDataGen());
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
            labels:{
                style:{
                    "font-size":"8px"
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
            style:{
                "font-size":"8px"
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
        },{
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
            visible:false
        },{
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
            visible:false
        }],
        global: {
            useUTC: false
        }

    };

    var randomDataGen = function () {
        var data = [], time = (new Date()).getTime(), i;
        for (i = -100; i <= 0; i += 1) {
            data.push({
                x: time + i * 1000,
                y: Math.random()
            });
        }
        return data;
    };
    $.fn.vectorDashboardGraph = function (job, device,color) {
        chartOptions["title"]= {
            text: job+":"+device
        };
        this.highcharts(chartOptions);
        Highcharts.setOptions(Highcharts.theme);
        console.log("yayyy !!!!");
        return this;
    };

}(jQuery));