/**
 * Created by MaN on 8/12/2016.
 */
VECTOR.namespace("module.carconfig");

VECTOR.module.carconfig = function() {
    var idPrefix = "#CC_";
    var setup = function(){
        fillActiveDevices();
        $(".block-device").draggable({
            revert:true,
            cursor: "hand"
        });
        $(".device-container").tooltip({
            position: {
                using: function( position, feedback ) {
                    $( this ).css( position );
                    $( "<div>" )
                        .addClass( "arrow" )
                        .addClass( feedback.vertical )
                        .addClass( feedback.horizontal )
                        .appendTo( this );
                }
            }
        });
        $(".device-area").droppable({
            accept:".block-device",
            over:function (event, ui) {
                $(this).css({'background-color':"#47759A"});
            },
            out:function (event, ui) {
                $(this).css({'background-color':'transparent'});
            },
            drop:function (event, ui) {
                ui.draggable.hide(300);
                $(this).find('.device-container').removeClass("container-not-filled");
                $('.container-not-filled').hide();
                $(this).css({'background-color':'transparent'});
                $(this).find('.device-container').css({'display':"block",'background-color':"#71d2a9"});
                $(this).find('.device-container').attr("value",ui.draggable.attr("value"));
                $(this).find('.device-container').attr("title",ui.draggable.attr("value"));
                $(this).find('.device-container').attr("status","filled");
                $(this).find('.device-container').hover(function () {
                    $(this).find('.device-clear').css({'display':"block"});
                },function () {
                    $(this).find('.device-clear').css({'display':"none"});
                });
                $(this).droppable("disable");
            },
            activate:function (event, ui) {
                $(this).find('.device-container').css({'display':"block",'background-color':"#47759A"});
            },
            deactivate:function (event, ui) {
                $('.container-not-filled').hide();
            }
        });
    };
    var fillActiveDevices = function(){
        var devices=[{value:"A001"},{value:"A002"},{value:"A003"},{value:"A004"},{value:"A005"},{value:"A006"},{value:"A007"}]; //devices should be extracted from master data
        var containerHeight=$(".vector-settings-bar").outerHeight(true)+10;
        _.each(devices,function (item) {
            var div;
            div='<div class="block-device ui-widget-content" value="';
            div+=item.value+'">'+item.value+'</div>';
            $(idPrefix+"activeDeviceInnerContainer").append(div);
            containerHeight+=$(".block-device").outerHeight(true);
        });
        $(idPrefix+"activeDeviceContainer").css({'height':containerHeight});
    };
    var clearClick = function () {
        $(this).parent().parent().droppable("enable");
        $(this).parent().hide();
        $(this).parent().removeClass('container-not-filled').addClass('container-not-filled');
        $(idPrefix+"activeDeviceInnerContainer").append('<div class="block-device ui-widget-content" value="'+$(this).parent().attr("value")+'">'+$(this).parent().attr("value")+'</div>');
        $(".block-device").draggable({
            revert:true
        });
    };
    return {
        init : function() {
            setup();
            $(".device-clear").off("click").on("click",clearClick);
        }
    }
}();

$(function() {
    VECTOR.module.carconfig.init();
});