/**
 * @author MaN
 on 8/20/2016.
 */
(function ($) {
    var chartOptions = {
        chart: {
            type: 'area',
            animation: Highcharts.svg, // don't animate in IE < IE 10.
            marginRight: 10,
            height: 195,
            width: 295,
            events: {
                load: function () {
                    // set up the updating of the chart each second
                    var series = this.series[0];
                    setInterval(function () {
                        series.setData(randomDataGen())
                    }, 1000);
                }
            }
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 10,
            labels:{
                style:{
                    "font-size":"8px"
                }
            }
        },
        yAxis: {
            title: {
                text: 'Value'
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
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{
            name: 'Random data',
            data: (function () {
                // generate an array of random data
                var data = [], time = (new Date()).getTime(), i;
                for (i = -100; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 100,
                        y: Math.random()
                    });
                }
                return data;
            }())
        }],
        global: {
            useUTC: false
        }

    };

    var randomDataGen = function () {
        var data = [], time = (new Date()).getTime(), i;
        for (i = -100; i <= 0; i += 1) {
            data.push({
                x: time + i * 100,
                y: Math.random()
            });
        }
        return data;
    };

    $.fn.vectorDashboardGraph = function (job, device,color) {
        chartOptions["title"]= {
            text: job+":"+device
        };
        chartOptions["colors"]=[color];
        this.highcharts(chartOptions);
        console.log("yayyy !!!!");
        return this;
    };

}(jQuery));