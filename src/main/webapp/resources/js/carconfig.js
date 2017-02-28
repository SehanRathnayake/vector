/**
 * Created by MaN on 8/12/2016.
 */
VECTOR.namespace("module.carconfig");

VECTOR.module.carconfig = function () {
    var idPrefix = "#CC_";
    //var ip = "192.168.43.150";
    var customerName;
    var vehicleName;
    var vehicleId;
    var wheelNameG;
    var jobId = 1;
    var dbJobId = -1;
    var subJobId;
    var pastJobId = 1;
    var ip = "localhost";
    var isNewCustomer = false;
    var firstFillDevices = true;
    var wheelNames = ["Front Left", "Front Right", "Rear Left", "Rear Right"];
    var devices = [{id: 1, value: "A001", status: "inactive"}, {id: 2, value: "A002", status: "inactive"}, {
        id: 3,
        value: "A003",
        status: "inactive"
    }, {id: 4, value: "A004", status: "inactive"}]; //devices should be extracted from master data
    var customerList = [{
        name: "Udith Rathnayake",
        vehicles: ["Toyota Vios 2007", "Hyundai Accent 2001"]
    }, {name: "Malith Fernando", vehicles: ["Suzuki Alto 2017"]},
        {
            name: "Supun Prelis", vehicles: ["Suzuki Alto 2013"]
        }, {name: "Anjana Gunasekara", vehicles: ["Honda Civic 2001"]},
        {name: "Asiri Karunathilake", vehicles: ["Nissan SunnyFB14 2001"]}];

    var fillActiveDevices = function () {
        var activeDevices;
        $.ajax({
            url: 'http://' + ip + ':8082/vector/rest/activeDevices',
            dataType: "json",
            cache: false,
            contentType: 'application/json;',
            type: 'POST',
            async: false,
            success: function (result) {
                activeDevices = result;
                _.each(devices, function (item) {
                    item.status = "inactive";
                });
                _.each(result, function (item) {
                    var d = _.where(devices, {id: item});
                    if (d.length > 0) {
                        d[0].status = "active";
                    }
                })
            }
        });

        var containerHeight = $(".vector-settings-bar").outerHeight(true) + 10;
        
        if (firstFillDevices) {
            $(idPrefix + "activeDeviceInnerContainer").empty();
            _.each(devices, function (item) {
                var div;
                div = '<div class="block-device ui-widget-content device-' + item.status + '" value="';
                div += item.value + '" id="' + item.id + '" title = "A00' + item.id + '"></div>';
                $(idPrefix + "activeDeviceInnerContainer").append(div);
                containerHeight += $(".block-device").outerHeight(true);
            });
            firstFillDevices=false;
        }
        var blockDevices = $(idPrefix + "activeDeviceInnerContainer").find(".block-device");
        _.each(blockDevices, function (block) {
            var filtered = _.filter(devices, function (x) {
                return x.id == parseInt($(block).attr("id"));
            });
            if (filtered.length > 0) {
                $(block).attr("status", filtered[0].status);
                $(block).removeClass("device-active");
                $(block).removeClass("device-inactive");
                $(block).addClass("device-"+filtered[0].status);
            }
        });
        $(idPrefix + "activeDeviceContainer").css({'height': '100%'});
        $(".device-active").draggable({
            revert: true,
            cursor: "hand"
        });
        $(".block-device").tooltip({
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

    };
    var fillAttachedDevices = function () {
        var activeDevices;
        $.ajax({
            url: 'http://' + ip + ':8082/vector/rest/activeDevices',
            dataType: "json",
            cache: false,
            contentType: 'application/json;',
            type: 'POST',
            async: false,
            success: function (result) {
                activeDevices = result;
                _.each(devices, function (item) {
                    item.status = "inactive";
                });
                _.each(result, function (item) {
                    var d = _.where(devices, {id: item});
                    if (d.length > 0) {
                        d[0].status = "active";
                    }
                })
            }
        });
        var containers = $(idPrefix + "wheelRow").find(".device-container");
        _.each(containers, function (conts) {
            if ($(conts).attr("started") == "false" && !$(conts).hasClass("container-not-filled")) {
                $(conts).removeClass("device-inactive").addClass("device-inactive");
                $(conts).removeClass("device-active");
            }
        });
        _.each(activeDevices, function (item) {
            _.each(containers, function (conts) {
                if ($(conts).attr("started") == "false" && !$(conts).hasClass("container-not-filled")) {
                    if ($(conts).attr("id") == item) {
                        $(conts).removeClass("device-active").addClass("device-active");
                        $(conts).removeClass("device-inactive");
                    }
                }
            })
        })
    };
    var clearClick = function (ele) {
        ele.parent().parent().droppable("enable");
        ele.parent().fadeOut(200);
        ele.parent().removeClass('container-not-filled').addClass('container-not-filled');
        ele.parent().removeClass('device-active');
        ele.parent().removeClass('device-inactive');
        $(idPrefix + "activeDeviceInnerContainer").append('<div class="block-device ui-widget-content device-active" status="active" id="'+parseInt(ele.parent().attr("id"))+'" title='+ele.parent().attr("value")+' value="' + ele.parent().attr("value") + '"></div>');
        $(".device-active").draggable({
            revert: true
        });
        var noOfDevices = ele.parent().parent().parent().find('.container-not-filled');
        if (noOfDevices.length != 0) {
            ele.parent().parent().parent().parent().parent().find('.btn-success').removeClass("disabled").addClass("disabled");
        }
        devices.push({id: parseInt(ele.parent().attr("id")), value: ele.parent().attr("value"), status: "active"});
    };

    var nextSection = function () {
        $(idPrefix + "detailSection").hide();
        $(idPrefix + "wheelConfigSection").show('slide', {direction: 'right'}, 500);
        customerName = $(idPrefix + "customerName").val();
        if ($(idPrefix + "vehicleBrand").hasClass("vector-hidden")) {
            vehicleName = $(idPrefix + "vehicleBrandCombo").html();
        } else {
            vehicleName = $(idPrefix + "vehicleBrand").val();
        }
    };

    var previousSection = function () {
        $(idPrefix + "wheelConfigSection").hide();
        $(idPrefix + "detailSection").show('slide', {direction: 'left'}, 500);
    };

    var addWheelConfig = function (ele, wheelName) {
        wheelNames = $.grep(wheelNames, function (e) {
            if (e == wheelName) {
                return false;
            } else {
                return true;
            }
        });
        var div = '<div class="col-lg-3 col-sm-4">'
            + '<div class="well well-inactive well-suspension" style="height:60%">'
            + '<div class="wheel-device-container">'

            + '</div>'
            + '<div class="wheel-select-popup dropdown">'
            + '<button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown" style="width: 100%;">Select a wheel'
            + '<span class="caret"></span></button>'
            + '<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">';
        _.each(wheelNames, function (item) {
            div += '<li class="wheel-select-li" role="presentation" value="' + item + '"><a role="menuitem" tabindex="-1" href="#">' + item + '</a></li>'
        });
        div += '</div>'
            + '</div>'
            + '<div class="well well-inactive " style="height:10%">'
            + '<div class="wheel-device-name">'
            + '</div>'
            + '</div>'
            + '<div class="btn btn-success btn-start disabled" style="height:10%;width:100%;font-size: 1.5vw;line-height:5%;padding-top: 1vw;" > Start'
            + '</div>'
            + '</div>';
        var progressDiv = '<div class = "vector-result-progress" id="CC_resultProgress' + wheelName.replace(/\s+/g, '') + '">'
            + '<div class="progress">'
            + ' <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width:0%">'
            + '<span class="sr-only">0% Complete</span>'
            + ' </div>'
            + '</div>'
            + ' </div>';
        ele.parent().parent().append(progressDiv);
        ele.parent().parent().find(".btn").attr("wheelName", wheelName.replace(/\s+/g, ''));
        var count = parseInt($(idPrefix + "wheelRow").attr("count"));
        if (count < 4) {
            count++;
            $(idPrefix + "wheelRow").append(div);
            $(idPrefix + "wheelRow").attr("count", count);
        }

        $(".device-active").draggable({
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
                // $(this).css({'background-color': "#47759A",'opacity':0.4});
            },
            out: function (event, ui) {
                $(this).css({'background-color': 'transparent'});
            },
            drop: function (event, ui) {
                ui.draggable.hide(300);
                $(this).find('.device-container').removeClass("container-not-filled");
                $('.container-not-filled').hide();
                $(this).css({'background-color': 'transparent'});
                $(this).find('.device-container').css({'display': "block"});
                $(this).find('.device-container').addClass("device-active");
                $(this).find('.device-container').attr("value", ui.draggable.attr("value"));
                $(this).find('.device-container').attr("title", ui.draggable.attr("value"));
                $(this).find('.device-container').attr("id", ui.draggable.attr("id"));
                $(this).find('.device-container').attr("status", "filled");
                $(this).find('.device-container').attr("started", false);
                $(this).find('.device-container').hover(function () {
                    $(this).find('.device-clear').css({'display': "block"});
                    $(this).find('.device-clear').attr("active", true);
                }, function () {
                    $(this).find('.device-clear').css({'display': "none"});
                    $(this).find('.device-clear').attr("active", false);
                });
                $(this).droppable("disable");
                devices = $.grep(devices, function (e) {
                    if (e.id == parseInt(ui.draggable.attr("id"))) {
                        return false;
                    } else {
                        return true;
                    }
                });
                var noOfDevices = $(this).parent().find('.container-not-filled');
                if (noOfDevices.length == 0) {
                    $(this).parent().parent().parent().find('.btn-success').removeClass("disabled");
                }
            },
            activate: function (event, ui) {
                $(this).find('.device-container').css({'display': "block"});
            },
            deactivate: function (event, ui) {
                $('.container-not-filled').hide();
            }
        });
        $(".wheel-select-li").off("click").on("click", function () {
            $(this).parent().parent().parent().find(".wheel-device-container").vectorWheelContent();
            $(this).parent().parent().parent().addClass("device-main-container");
            addWheelConfig($(this).parent().parent().parent().find(".wheel-device-container"), $(this).attr("value"));
            $(".wheel-select-popup").hide();
            $(this).parent().parent().parent().parent().find(".wheel-device-name").html($(this).attr("value"));
            wheelNameG = $(this).attr("value");
            $(".device-clear").off("click").on("click", function (e) {
                e.stopPropagation();
                clearClick($(this));
            });
        });
        $('.btn-start').off("click").on("click", function () {

            if(dbJobId==-1){
                setDbJobId();
            }
            setSubJobId();
            var devices = $(this).parent().find('.device-container');
            var btn = this;
            var deviceIds = [];
            _.each(devices, function (item) {
                var deviceWheel = {};
                deviceWheel.deviceId = parseInt($(item).attr("id"));
                deviceWheel.wheelName = wheelName;
                deviceWheel.customerName = customerName;
                deviceWheel.vehicleName = vehicleName;
                deviceWheel.jobId = dbJobId;
                deviceIds.push(deviceWheel);
                $(item).attr("started", true);
            });
            $.ajax({
                url: 'http://' + ip + ':8082/vector/rest/activate',
                dataType: "json",
                data: JSON.stringify(deviceIds),
                cache: false,
                contentType: 'application/json;',
                type: 'POST',
                success: function (result) {
                    var x = result;
                    $(btn).removeClass("btn-success");
                    $(btn).removeClass("btn-warning").addClass("btn-warning");
                    $(btn).removeClass("disabled").addClass("disabled");
                    var timesRun = 0;
                    $(idPrefix + "resultProgress" + $(btn).attr("wheelName")).show();
                    var interval = setInterval(function () {
                        timesRun += 1;
                        $(idPrefix + "resultProgress" + $(btn).attr("wheelName")).find(".progress-bar").css({'width': 100 / 80 * parseInt(timesRun) + '%'});
                        $(idPrefix + "resultProgress" + $(btn).attr("wheelName")).find(".progress-bar").attr("aria-valuenow", 100 / 80 * parseInt(timesRun));
                        $(btn).html("Wait " + (80 - parseInt(timesRun)) + " seconds !");
                        if (timesRun === 80) {
                            clearInterval(interval);
                            $(idPrefix + "resultProgress" + $(btn).attr("wheelName")).hide();
                            $.ajax({
                                url: 'http://' + ip + ':8082/vector/rest/results',
                                dataType: "json",
                                data: JSON.stringify(deviceIds),
                                cache: false,
                                contentType: 'application/json;',
                                type: 'POST',
                                success: function (result) {
                                    var x = result;
                                    if (x) {
                                        $(btn).removeClass("btn-warning");
                                        $(btn).removeClass("btn-info").addClass("btn-info");
                                        $(btn).html("Results");
                                        $(btn).removeClass("btn-start");
                                        $(btn).removeClass("disabled");
                                        $(btn).removeClass("btn-results").addClass("btn-results");
                                        _.each(devices, function (item) {
                                            $(item).attr("started", false);
                                        });
                                        $('.btn-results').off("click").on("click", function () {
                                            $("#RS_resultSec").show();
                                            deviceIds[0].subJob = subJobId;
                                            deviceIds[1].subJob = subJobId;
                                            VECTOR.module.wheelresults.getCharts(deviceIds,true);
                                        });
                                    } else {
                                        $(btn).html("Something went\nwrong");
                                        $(btn).removeClass("btn-warning");
                                        $(btn).removeClass("btn-danger").addClass("btn-danger");
                                        $(btn).removeClass("disabled").addClass("disabled");
                                    }

                                }
                            });

                        }
                        //do whatever here..
                    }, 1000);

                }
            });
        });

    };

    var customerSearch = function (event) {
        if (!isNewCustomer) {
            var str = $(idPrefix + "customerName").val();
            var filteredList = _.filter(customerList, function (num) {
                return num.customer.custName.indexOf(str) >= 0;
            });
            $(idPrefix + "customerSearchList").empty();
            _.each(filteredList, function (item) {
                //var div='<div class="customer-search-item" style="padding-left: 5px;">'+item+'</div>';
                var div = '<li class="customer-search-item" role="presentation"><a role="menuitem" tabindex="-1"'
                    + 'href="#">' + item.customer.custName + '</a>'
                    + '</li>';
                $(idPrefix + "customerSearchList").append(div);
            });
            if (filteredList.length > 0) {
                $(idPrefix + "customerSearchList").show();
            }
            $(".customer-search-item").off("click").on("click", function () {
                $(idPrefix + "customerName").val($(this).find('a').html());
                $(idPrefix + "customerSearchList").hide();
                $(idPrefix + "vehicleBrandList").empty();
                $(idPrefix + "vehicleBrandCombo").show();
                $(idPrefix + "vehicleBrandCombo").html("Select Vehicle");
                $(idPrefix + "jobIdCombo").html("Job Id");
                $(idPrefix + "jobIdCombo").removeClass("disabled").addClass("disabled");
                disablePastResults();
                $(idPrefix + "vehicleBrand").hide();
                customerName = $(idPrefix + "customerName").val();
                $(idPrefix + "vehicleBrand").removeClass("vector-hidden").addClass("vector-hidden");
                var vehicleList = _.where(customerList, {customerName: $(idPrefix + "customerName").val()});
                _.each(vehicleList[0].vehicles, function (item) {
                    //var div='<div class="customer-search-item" style="padding-left: 5px;">'+item+'</div>';
                    var div1 = '<li class="vehicle-brand-item" role="presentation" id = "'+item.vehicleId+'"><a role="menuitem" tabindex="-1"'
                        + 'href="#">' + item.manufacturer + " " + item.type + '</a>'
                        + '</li>';
                    $(idPrefix + "vehicleBrandList").append(div1);
                });
                $(".vehicle-brand-item").off("click").on("click", function () {
                    vehicleId = $(this).attr("id");
                    $(idPrefix + "vehicleBrandCombo").html($(this).find('a').html());
                    vehicleName = $(idPrefix + "vehicleBrandCombo").html();
                    $(idPrefix + "jobIdCombo").html("Job Id");
                    disablePastResults();
                    $(idPrefix + "nextBtn").removeClass("disabled");
                    getJobId();
                    setPastJobs(parseInt($(this).attr("id")));
                });
            });
        }
    };
    var disablePastResults = function () {
        $(idPrefix + "frontLeftBtn").removeClass("disabled").addClass("disabled");
        $(idPrefix + "frontRightBtn").removeClass("disabled").addClass("disabled");
        $(idPrefix + "rearLeftBtn").removeClass("disabled").addClass("disabled");
        $(idPrefix + "rearRightBtn").removeClass("disabled").addClass("disabled");
    };
    var showNewDetails = function () {
        if ($(idPrefix + "newCustomerSec").hasClass("vector-hidden")) {
            $(idPrefix + "newCustomerSec").slideDown(300);
            $(idPrefix + "newVehicleSec").slideDown(300);
            $(idPrefix + "newCustomerSec").removeClass("vector-hidden");
            $(idPrefix + "newVehicleSec").removeClass("vector-hidden");
            $(idPrefix + "vehicleBrandCombo").hide();
            $(idPrefix + "vehicleBrand").show();
            $(idPrefix + "vehicleBrand").removeClass("vector-hidden");
            $(idPrefix + "customerName").val("");
            $(idPrefix + "nextBtn").removeClass("disabled").addClass("disabled");
            $(idPrefix + "jobIdCombo").removeClass("disabled").addClass("disabled");
            $(idPrefix + "pastResults").slideUp(300);
            disablePastResults();
            isNewCustomer = true;
            jobId = 1;
        } else {
            $(idPrefix + "newCustomerSec").slideUp(300);
            $(idPrefix + "newVehicleSec").slideUp(300);
            $(idPrefix + "pastResults").slideDown(300);
            $(idPrefix + "newCustomerSec").removeClass("vector-hidden").addClass("vector-hidden");
            $(idPrefix + "newVehicleSec").removeClass("vector-hidden").addClass("vector-hidden");
            isNewCustomer = false;
        }
    };
    var getJobId = function () {
        $.ajax({
            url: 'http://' + ip + ':8082/vector/getJobId',
            dataType: "json",
            data: customerName + "_" + vehicleName,
            cache: false,
            contentType: 'application/json;',
            type: 'POST',
            async: false,
            success: function (result) {
                jobId = result;
            }
        });
    };
    var setPastJobs = function (id) {
        var jobIds;
        $.ajax({
            url: 'http://' + ip + ':8082/vector/getJobIdList',
            dataType: "json",
            data: JSON.stringify(id),
            cache: false,
            contentType: 'application/json;',
            type: 'POST',
            async: false,
            success: function (result) {
                jobIds = result;
            }
        });
        $(idPrefix + "jobIdUl").empty();
        if (jobIds.length > 0) {
            $(idPrefix + "jobIdCombo").removeClass("disabled");
        }
        _.each(jobIds,function (item) {
            var div = '<li class="job-item" role="presentation" id="'+item.jobId+'"><a role="menuitem" tabindex="-1"'
                + 'href="#">' + "Job " +item.jobId+ '</a>'
                + '</li>';
            $(idPrefix + "jobIdUl").append(div);
        });
        /*for (var i = 0; i < jobId - 1; i++) {
            var div = '<li class="job-item" role="presentation"><a role="menuitem" tabindex="-1"'
                + 'href="#">' + "Job " + (i + 1) + '</a>'
                + '</li>';
            $(idPrefix + "jobIdUl").append(div);
        }*/
        $(".job-item").off("click").on("click", function () {
            $(idPrefix + "jobIdCombo").html($(this).find('a').html());
            var jI = $(this).find('a').html().split(" ");
            pastJobId = jI[1];
            disablePastResults();
            $.ajax({
                url: 'http://' + ip + ':8082/vector/getSubJobIdList',
                dataType: "json",
                data: $(this).attr("id"),
                cache: false,
                contentType: 'application/json;',
                type: 'POST',
                success: function (result) {
                    _.each(result, function (item,key) {
                        switch (key) {
                            case "FRONT LEFT":
                                $(idPrefix + "frontLeftBtn").removeClass("disabled");
                                $(idPrefix + "frontLeftBtn").attr("subJobId",item);
                                break;
                            case "FRONT RIGHT":
                                $(idPrefix + "frontRightBtn").removeClass("disabled");
                                $(idPrefix + "frontRightBtn").attr("subJobId",item);
                                break;
                            case "REAR LEFT":
                                $(idPrefix + "rearLeftBtn").removeClass("disabled");
                                $(idPrefix + "rearLeftBtn").attr("subJobId",item);
                                break;
                            case "REAR RIGHT":
                                $(idPrefix + "rearRightBtn").removeClass("disabled");
                                $(idPrefix + "rearRightBtn").attr("subJobId",item);
                                break;
                        }
                    })
                }
            });
        });
    };
    var getPastResults = function (wheelName,subId) {
        var devices = [1, 2];
        var deviceIds = [];
        _.each(devices, function (item) {
            var deviceWheel = {};
            deviceWheel.deviceId = item;
            deviceWheel.wheelName = wheelName;
            deviceWheel.customerName = customerName;
            deviceWheel.vehicleName = vehicleName;
            deviceWheel.jobId = pastJobId;
            deviceWheel.subJob = subId;
            deviceIds.push(deviceWheel);
        });
        $("#RS_resultSec").show();
        VECTOR.module.wheelresults.getCharts(deviceIds,false);
    };
    var refreshPage = function () {
        $.ajax({
            url: 'http://' + ip + ':8082/vector/newJob',
            dataType: "json",
            cache: false,
            contentType: 'application/json;',
            type: 'GET',
            async: false,
            success: function (result) {
            }
        });
    };
    var setDbJobId = function () {
        $.ajax({
            url: 'http://' + ip + ':8082/vector/createNewJob',
            dataType: "json",
            data:vehicleId,
            cache: false,
            contentType: 'application/json;',
            type: 'POST',
            async: false,
            success: function (result) {
                dbJobId=result
            }
        });
    };
    var setSubJobId = function () {
        var data = dbJobId+"_"+wheelNameG;
        $.ajax({
            url: 'http://' + ip + ':8082/vector/createSubJob',
            dataType: "json",
            cache: false,
            data:data,
            contentType: 'application/json;',
            type: 'POST',
            async: false,
            success: function (result) {
                subJobId= result;
            }
        });
    };
    var getCustomerList = function () {
        $.ajax({
            url: 'http://' + ip + ':8082/vector/customerVehicleList',
            dataType: "json",
            cache: false,
            data: "sa",
            contentType: 'application/json;',
            type: 'POST',
            success: function (result) {
                customerList = result;
            }
        });
    };
    var addNewCustomer = function () {
        var newCustomer = {};
        $.ajax({
            url: 'http://' + ip + ':8082/vector/addCustomer',
            dataType: "json",
            cache: false,
            data: JSON.stringify(newCustomer),
            contentType: 'application/json;',
            type: 'POST',
            success: function (result) {
                customerList = result;
            }
        });
    };
    return {
        init: function () {
            getCustomerList();
            fillActiveDevices();
            //getCustomerList();
            $(idPrefix + "nextBtn").off("click").on("click", nextSection);
            //$(idPrefix + "refreshBtn").off("click").on("click", refreshPage);
            //$(idPrefix + "newCustomer").off("click").on("click", showNewDetails);
            $(idPrefix + "prevBtn").off("click").on("click", previousSection);
            $('body').off("click").on("click", '.well-inactive', function () {
                $(this).find(".wheel-select-popup").css({"display": "block"});
            });
            $(".wheel-select-li").off("click").on("click", function () {
                $(this).parent().parent().parent().find(".wheel-device-container").vectorWheelContent();
                $(this).parent().parent().parent().addClass("device-main-container");
                addWheelConfig($(this).parent().parent().parent().find(".wheel-device-container"), $(this).attr("value"));
                $(".wheel-select-popup").hide();
                wheelNameG = $(this).attr("value");
                $(this).parent().parent().parent().parent().find(".wheel-device-name").html($(this).attr("value"));
                $(".device-clear").off("click").on("click", function (e) {
                    e.stopPropagation();
                    clearClick($(this));
                });
            });
            $(".job-select-li").off("click").on("click", function () {
                $(idPrefix + "jobTypeMenu").html($(this).find('a').html());
            });
            $(".past-result-btn").off("click").on("click", function () {
                getPastResults($(this).html(),$(this).attr("subJobId"));
            });
            window.setInterval(function () {
                fillActiveDevices();
            }, 10000);
            var fillAttachedDevicesInt = setInterval(function () {
                fillAttachedDevices();
            }, 5000);
            $(idPrefix + "customerName").keyup(function (event) {
                customerSearch(event);
            });
            $(idPrefix + "vehicleBrand").keyup(function (event) {
                if ($(idPrefix + "vehicleBrand").val().length > 0) {
                    $(idPrefix + "nextBtn").removeClass("disabled");
                } else {
                    $(idPrefix + "nextBtn").removeClass("disabled").addClass("disabled");
                }
            });
            $(document.body).on('click', function () {
                $(idPrefix + "customerSearchList").hide();
            });

        }
    }
}();

$(function () {
    VECTOR.module.carconfig.init();
});