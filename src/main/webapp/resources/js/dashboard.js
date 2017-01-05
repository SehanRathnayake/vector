/**
 * @author MaN
 on 8/7/2016.
 */
VECTOR.namespace("module.dashboard");

VECTOR.module.dashboard = function () {
    var idPrefix = "#DB_";

    //add newly created graph js into url array
    var graphUrls = ["resources/js/samplegraph.js"];

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
    var setup = function(){
        $(".block-graph").draggable({
            revert:true
        });
        $(".block-device").draggable({
            revert:true
        });
        $(".block-graph").append('<div class="highchart-plaster"></div>');
    };

    var setupCharts = function () {
        $("#DB_container_A001").vectorDashboardGraph("J-02","001");
        $("#DB_container_A002").vectorDashboardGraph("J-02","002");
        $("#DB_container_A003").vectorDashboardGraph("J-02","003");
        $("#DB_container_A004").vectorDashboardGraph("J-02","004");
        $("#DB_container_A005").vectorDashboardGraph("J-02","005");
    };
    return {
        init: function () {
            loadScript(null);
            setup();
            setupCharts()
        }
    }
}();

$(function () {
    VECTOR.module.dashboard.init();
});
