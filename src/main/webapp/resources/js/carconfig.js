/**
 * Created by MaN on 8/12/2016.
 */
VECTOR.namespace("module.carconfig");

VECTOR.module.carconfig = function () {
    var idPrefix = "#CC_";
    var wheelNames = ["Front Left", "Front Right", "Rear Left", "Rear Right"];
    var setup = function () {
        $(".block-device").draggable({
            revert: true,
            cursor: "hand"
        });
        $(".device-container").tooltip({
            position: {
                using: function (position, feedback) {
                    $(this).css(position);
                    $("<div>")
                        .addClass("arrow")
                        .addClass(feedback.vertical)
                        .addClass(feedback.horizontal)
                        .appendTo(this);
                }
            }
        });
        $(".device-area").droppable({
            accept: ".block-device",
            over: function (event, ui) {
                $(this).css({'background-color': "#47759A"});
            },
            out: function (event, ui) {
                $(this).css({'background-color': 'transparent'});
            },
            drop: function (event, ui) {
                ui.draggable.hide(300);
                $(this).find('.device-container').removeClass("container-not-filled");
                $('.container-not-filled').hide();
                $(this).css({'background-color': 'transparent'});
                $(this).find('.device-container').css({'display': "block", 'background-color': "#71d2a9"});
                $(this).find('.device-container').attr("value", ui.draggable.attr("value"));
                $(this).find('.device-container').attr("title", ui.draggable.attr("value"));
                $(this).find('.device-container').attr("status", "filled");
                $(this).find('.device-container').hover(function () {
                    $(this).find('.device-clear').css({'display': "block"});
                    $(this).find('.device-clear').attr("active", true);
                }, function () {
                    $(this).find('.device-clear').css({'display': "none"});
                    $(this).find('.device-clear').attr("active", false);
                });
                $(this).droppable("disable");
            },
            activate: function (event, ui) {
                $(this).find('.device-container').css({'display': "block", 'background-color': "#47759A"});
            },
            deactivate: function (event, ui) {
                $('.container-not-filled').hide();
            }
        });
    };
    var fillActiveDevices = function () {
        var devices = [{value: "A001"}, {value: "A002"}, {value: "A003"}, {value: "A004"}, {value: "A005"}, {value: "A006"}, {value: "A007"}]; //devices should be extracted from master data
        var containerHeight = $(".vector-settings-bar").outerHeight(true) + 10;
        _.each(devices, function (item) {
            var div;
            div = '<div class="block-device ui-widget-content" value="';
            div += item.value + '">' + item.value + '</div>';
            $(idPrefix + "activeDeviceInnerContainer").append(div);
            containerHeight += $(".block-device").outerHeight(true);
        });
        $(idPrefix + "activeDeviceContainer").css({'height': '100%'});
    };
    var clearClick = function (ele) {
        ele.parent().parent().droppable("enable");
        ele.parent().fadeOut(200);
        ele.parent().removeClass('container-not-filled').addClass('container-not-filled');
        $(idPrefix + "activeDeviceInnerContainer").append('<div class="block-device ui-widget-content" value="' + ele.parent().attr("value") + '">' + ele.parent().attr("value") + '</div>');
        $(".block-device").draggable({
            revert: true
        });
    };

    var nextSection = function () {
        $(idPrefix + "detailSection").hide();
        $(idPrefix + "wheelConfigSection").show('slide', {direction: 'right'}, 700);
    };

    var previousSection = function () {
        $(idPrefix + "detailSection").show('slide', {direction: 'left'}, 700);
        $(idPrefix + "wheelConfigSection").hide();
    };

    var addWheelConfig = function (ele, wheelName) {
        wheelNames = $.grep(wheelNames, function (e) {
            if (e == wheelName) {
                return false;
            } else {
                return true;
            }
        });
        var div = '<div class="col-lg-3">'
            + '<div class="well well-inactive well-suspension" style="height:60%">'
            + '<div class="wheel-device-container">'

            + '</div>'
            + '<div class="wheel-select-popup dropdown">'
            + '<button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown" style="width: 100%;">Select a wheel'
            + '<span class="caret"></span></button>'
            + '<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">';
        _.each(wheelNames, function (item) {
            div += '<li class="wheel-select-li" role="presentation" value="'+item+'"><a role="menuitem" tabindex="-1" href="#">' + item + '</a></li>'
        });
        div += '</div>'
            + '</div>'
            + '<div class="well well-inactive " style="height:10%">'
            + '<div class="wheel-device-name">'
            + '</div>'
            + '</div>'
            + '<div class="btn btn-success disabled" style="height:10%;width:100%" > Start'
            + '</div>'
            + '</div>';
        var count = parseInt($(idPrefix + "wheelRow").attr("count"));
        if (count < 4) {
            count++;
            $(idPrefix + "wheelRow").append(div);
            $(idPrefix + "wheelRow").attr("count", count);
        }

        $(".block-device").draggable({
            revert: true,
            cursor: "hand"
        });
        ele.find(".device-container").tooltip({
            position: {
                using: function (position, feedback) {
                    $(this).css(position);
                    $("<div>")
                        .addClass("arrow")
                        .addClass(feedback.vertical)
                        .addClass(feedback.horizontal)
                        .appendTo(this);
                }
            }
        });
        ele.find(".device-area").droppable({
            accept: ".block-device",
            over: function (event, ui) {
                $(this).css({'background-color': "#47759A"});
            },
            out: function (event, ui) {
                $(this).css({'background-color': 'transparent'});
            },
            drop: function (event, ui) {
                ui.draggable.hide(300);
                $(this).find('.device-container').removeClass("container-not-filled");
                $('.container-not-filled').hide();
                $(this).css({'background-color': 'transparent'});
                $(this).find('.device-container').css({'display': "block", 'background-color': "#71d2a9"});
                $(this).find('.device-container').attr("value", ui.draggable.attr("value"));
                $(this).find('.device-container').attr("title", ui.draggable.attr("value"));
                $(this).find('.device-container').attr("status", "filled");
                $(this).find('.device-container').hover(function () {
                    $(this).find('.device-clear').css({'display': "block"});
                    $(this).find('.device-clear').attr("active", true);
                }, function () {
                    $(this).find('.device-clear').css({'display': "none"});
                    $(this).find('.device-clear').attr("active", false);
                });
                $(this).droppable("disable");
            },
            activate: function (event, ui) {
                $(this).find('.device-container').css({'display': "block", 'background-color': "#47759A"});
            },
            deactivate: function (event, ui) {
                $('.container-not-filled').hide();
            }
        });
        $(".wheel-select-li").off("click").on("click", function () {
            $(this).parent().parent().parent().find(".wheel-device-container").vectorWheelContent();
            addWheelConfig($(this).parent().parent().parent().find(".wheel-device-container"), $(this).attr("value"));
            $(".wheel-select-popup").hide();
            $(this).parent().parent().parent().parent().find(".wheel-device-name").html($(this).attr("value"));
            $(".device-clear").off("click").on("click", function (e) {
                e.stopPropagation();
                clearClick($(this));
            });
        });

    };
    return {
        init: function () {
            fillActiveDevices();
            $(idPrefix + "nextBtn").off("click").on("click", nextSection);
            $(idPrefix + "prevBtn").off("click").on("click", previousSection);
            $('body').off("click").on("click", '.well-inactive', function () {
                $(this).find(".wheel-select-popup").css({"display": "block"});
            });
            $(".wheel-select-li").off("click").on("click", function () {
                $(this).parent().parent().parent().find(".wheel-device-container").vectorWheelContent();
                addWheelConfig($(this).parent().parent().parent().find(".wheel-device-container"), $(this).attr("value"));
                $(".wheel-select-popup").hide();
                $(this).parent().parent().parent().parent().find(".wheel-device-name").html($(this).attr("value"));
                $(".device-clear").off("click").on("click", function (e) {
                    e.stopPropagation();
                    clearClick($(this));
                });
            });
        }
    }
}();

$(function () {
    VECTOR.module.carconfig.init();
});