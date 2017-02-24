/**
 * @author MaN
 on 8/20/2016.
 */
(function ($) {

    var div = '<div class="device-im">'
        +'</div><div class="device-area suspension-part-up">'
        + '<div class="device-container container-not-filled">'
        + '<div class="device-clear"></div>'
        + '</div>'
        + '</div>'
        + '<div class="device-area suspension-part-down">'
        + '<div class="device-container container-not-filled">'
        + '<div class="device-clear"></div>'
        + '</div>'
        + '</div>';

    $.fn.vectorWheelContent = function () {
        this.empty();
        this.parent().removeClass("well-suspension");
        this.parent().parent().find(".well").removeClass("well-inactive");
        this.append(div);
        console.log("yayyy !!!!");
        //setInterval(generateSampleDataBuffer,1000);
        return this;
    };

}(jQuery));