/**
 * @author MaN
 on 8/7/2016.
 */
VECTOR.namespace("module.dashboard");

VECTOR.module.dashboard = function () {
    var idPrefix = "#DB_";
    //add newly created graph js into url array
    var graphUrls = ["resources/js/samplegraph.js",
                     "resources/js/highchartGraph.js"];

    var loadScript = function (callback) {
        var head = document.getElementsByTagName('head')[0];
        _.each(graphUrls, function (item) {
            var script = document.createElement('script');
            script.type = 'text/javascript';
            script.src = item;

            // Then bind the event to the callback function.
            // There are several events for cross browser compatibility.
            script.onreadystatechange = callback;
            script.onload = callback;

            // Fire the loading
            head.appendChild(script);
        });

    };

    return {
        init: function () {
            loadScript(null);
        }
    }
}();

$(function () {
    VECTOR.module.dashboard.init();
});
