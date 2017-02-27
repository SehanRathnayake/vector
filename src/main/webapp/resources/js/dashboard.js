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
    var dash = function () {
        $("#dash_animation").addClass('animated ').addClass('bounceInLeft');
    }
    var slideIndex = 0;
    var slideshow = function showSlides() {
        var i;
        var slides = document.getElementsByClassName("mySlides");
        var dots = document.getElementsByClassName("dot");
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slideIndex++;
        if (slideIndex > slides.length) {
            slideIndex = 1
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " active";
        setTimeout(showSlides, 2000); // Change image every 2 seconds
    }

    var setup = function () {

        $(".block-graph").draggable({
            revert: true
        });
        $(".block-device").draggable({
            revert: true
        });


        $(".block-graph").append('<div class="highchart-plaster"></div>');
    };

    var slideIndex = 1;




    var setupCharts = function () {
        $("#DB_container_A001").vectorDashboardGraph("J-02", "001");
        $("#DB_container_A002").vectorDashboardGraph("J-02", "002");
        $("#DB_container_A003").vectorDashboardGraph("J-02", "003");
        $("#DB_container_A004").vectorDashboardGraph("J-02", "004");
        $("#DB_container_A005").vectorDashboardGraph("J-02", "005");
    };
    return {
        init: function () {
            loadScript(null);
            dash();
            slideshow();
            setup();


            setupCharts();

        }
    }
}();

$(function () {
    VECTOR.module.dashboard.init();
});
