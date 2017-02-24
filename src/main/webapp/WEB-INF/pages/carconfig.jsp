<%--
  Created by IntelliJ IDEA.
  User: MaN
  Date: 8/12/2016
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>
<script src="<c:url value="/resources/js/carconfig.js" />"></script>
<script src="<c:url value="/resources/js/home.js" />"></script>
<script src="<c:url value="/resources/js/wheelwidget.js" />"></script>
<script src="<c:url value="/resources/js/wheelresults.js" />"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<script src="<c:url value="/resources/js/jquery/jquery.ui.touch-punch.min.js" />"></script>
<script src="<c:url value="/resources/js/util/processing.min.js" />"></script>
<div class="vector-header" style="
    font-size: 30px;
    font-family: inherit;
    line-height: 2;
    padding: 8px;
    color: white;
">
    Hi, <security:authentication property="principal.fullname"/>.
    <a href="${logoutUrl}" style="
    float: right;
        font-size: 15px;
    margin-right: 10px;
    color: whitesmoke;
">Log out</a>
</div>
<div>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
            </div>
            <ul class="nav navbar-nav">
                <li><a href="home">Dashboard</a></li>
                <li><a href="customer">Customer</a></li>
                <li><a href="vehicle">Vehicle</a></li>
                <li style="
    float: right;
    background-color: '#47759a';
">
                    <a href="newJob">Create new job</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<%--<div class="row" id="CC_progressBar">
    <div class="row">
        <div class="label-primary" style="padding-left: 50px">Progress Bar</div>
    </div>
</div>--%>
<div id="CC_sectionContainer">
<section id="CC_detailSection" class="configsection">
    <div class="container well">
        <div class="row vector-row">
            <div class="col-lg-3 col-sm-3">
                <div>Job Type</div>
            </div>
            <div class="col-lg-3 col-sm-3">
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="CC_jobTypeMenu"
                            data-toggle="dropdown" style="width: 68%;">Suspension check
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="jobTypeMenu">
                        <li class="job-select-li" role="presentation" value="Front Left"><a role="menuitem"
                                                                                            tabindex="-1"
                                                                                            href="#">Suspension
                            check</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-3 col-sm-3"></div>
            <div class="col-lg-3 col-sm-3"></div>
        </div>
    </div>
    <div class="container well">
        <div class="row vector-row">
            <div class="col-lg-3 col-sm-3">
                <div class>Customer Name</div>
            </div>
            <div class="col-lg-3 col-sm-3">
                <input id="CC_customerName"></input>
                <div class="dropdown">
                    <ul class="dropdown-menu" id="CC_customerSearchList" role="menu" aria-labelledby="jobTypeMenu">

                    </ul>
                </div>
            </div>
            <div class="col-lg-3 col-sm-3">
                <a id="CC_newCustomer" style="width: 100%;">New Customer
                </a>
            </div>
            <div class="col-lg-3 col-sm-3"></div>
        </div>
        <div id="CC_newCustomerSec" class="vector-hidden" style="display: none">
            <div class="row vector-row">
                <div class="col-lg-3 col-sm-3">
                    <div class>Address</div>
                </div>
                <div class="col-lg-3 col-sm-3">
                    <textarea id="CC_customerAddress"></textarea>
                </div>
            </div>
            <div class="row vector-row">
                <div class="col-lg-3">
                    <div class>Phone</div>
                </div>
                <div class="col-lg-3 col-sm-3">
                    <input id="CC_customerPhone"></input>
                </div>
            </div>
            <div class="row vector-row">
                <div class="col-lg-3 col-sm-3">
                    <div class>Email</div>
                </div>
                <div class="col-lg-3 col-sm-3">
                    <input id="CC_customerEmail"></input>
                </div>
            </div>
        </div>
    </div>
    <div class="container well">
        <div class="row vector-row">
            <div class="col-lg-3 col-sm-3">
                <div class>Vehicle Name</div>
            </div>
            <div class="col-lg-3 col-sm-3">
                <input id="CC_vehicleBrand"></input>
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="CC_vehicleBrandCombo"
                            data-toggle="dropdown" style="width: 68%; display:none">Select Vehicle
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu" id="CC_vehicleBrandList" role="menu"
                        aria-labelledby="CC_vehicleBrandCombo">

                    </ul>
                </div>
            </div>
        </div>
        <div id="CC_newVehicleSec" class="vector-hidden" style="display: none">
            <div class="row vector-row">
                <div class="col-lg-3 col-sm-3">
                    <div class>Model year</div>
                </div>
                <div class="col-lg-3 col-sm-3">
                    <input id="CC_modelYear"></input>
                    <button class="btn btn-default dropdown-toggle" type="button" id="CC_modelYearCombo"
                            data-toggle="dropdown" style="width: 100%; display:none">Select Model Year
                        <span class="caret"></span></button>
                </div>
            </div>
            <div class="row vector-row">
                <div class="col-lg-3 col-sm-3">
                    <div class>Registration Number</div>
                </div>
                <div class="col-lg-3 col-sm-3">
                    <input id="CC_vehicalRegNo"></input>
                </div>
            </div>
        </div>
    </div>
    <div class="container well" id="CC_pastResults">
        <div class="row vector-row">
            <div class="col-lg-3 col-sm-3">
                <div class="col-lg-4 col-sm-4">
                    <div class>Past results</div>
                </div>
                <div class="col-lg-8 col-sm-8">
                    <button class="btn btn-default dropdown-toggle disabled" type="button" id="CC_jobIdCombo"
                            data-toggle="dropdown" style="width: 68%;">Job Id
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="jobTypeMenu" id="CC_jobIdUl">
                    </ul>
                </div>
            </div>
            <div class="col-lg-2 col-sm-2">
                <button class="btn btn-default disabled past-result-btn" type="button" id="CC_frontLeftBtn"
                        data-toggle="dropdown" style="width: 68%">Front Left</button>
            </div>
            <div class="col-lg-2 col-sm-2">
                <button class="btn btn-default disabled past-result-btn" type="button" id="CC_frontRightBtn"
                        data-toggle="dropdown" style="width: 68%">Front Right</button>
            </div>
            <div class="col-lg-2 col-sm-2">
                <button class="btn btn-default disabled past-result-btn" type="button" id="CC_rearLeftBtn"
                        data-toggle="dropdown" style="width: 68%">Rear Left</button>
            </div>
            <div class="col-lg-2 col-sm-2">
                <button class="btn btn-default disabled past-result-btn" type="button" id="CC_rearRightBtn"
                        data-toggle="dropdown" style="width: 68%">Rear Right</button>
            </div>
        </div>
    </div>
    <div class="row config-nav-buttons">
        <div class="col-lg-12 col-sm-12">
            <div class="btn btn-primary right-float disabled" id="CC_nextBtn">Next</div>
            <div class="btn btn-primary right-float" id="CC_refreshBtn"><a href="newJob" style="    color: white;
    text-decoration: none; ">Refresh to new Job</a></div>
        </div>
    </div>

</section>
<section class="wheelsection" id="CC_wheelConfigSection" style="display: none">
    <div class="container" style="height:70%">
        <div class="row wheel-device">
            <div class="col-lg-2 col-sm-2">
                <div class="col-lg-10 col-sm-12">
                    <div class="well" id="CC_activeDeviceInnerContainer">

                    </div>
                </div>
            </div>
            <div class="col-lg-10 col-sm-10" id="CC_wheelRow" count="1">

                <div class="col-lg-3 col-sm-4">
                    <div class="well well-suspension well-inactive" style="height:60%">
                        <div class="wheel-device-container">

                        </div>
                        <div class="wheel-select-popup dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="menu1"
                                    data-toggle="dropdown" style="width: 100%;">Select a wheel
                                <span class="caret"></span></button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                                <li class="wheel-select-li" role="presentation" value="Front Left"><a role="menuitem"
                                                                                                      tabindex="-1"
                                                                                                      href="#">Front
                                    Left</a></li>
                                <li class="wheel-select-li" role="presentation" value="Front Right"><a role="menuitem"
                                                                                                       tabindex="-1"
                                                                                                       href="#">Front
                                    Right</a></li>
                                <li class="wheel-select-li" role="presentation" value="Rear Left"><a role="menuitem"
                                                                                                     tabindex="-1"
                                                                                                     href="#">Rear
                                    Left</a></li>
                                <li class="wheel-select-li" role="presentation" value="Rear Right"><a role="menuitem"
                                                                                                      tabindex="-1"
                                                                                                      href="#">Rear
                                    Right</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="well well-inactive" style="height:10%">
                        <div class="wheel-device-name">

                        </div>
                    </div>
                    <div class="btn btn-success btn-start disabled" style="height:10%;width:100%;font-size: 1.5vw;line-height:5%;padding-top: 1vw;">
                        Start
                    </div>
                    <div class = "vector-result-progress" id="CC_resultProgressRearLeft">
                        <div>Wait 70 seconds</div>
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width:0%">
                                <span class="sr-only">0% Complete</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row config-nav-buttons">
        <div class="col-lg-12">
            <div class="btn btn-primary right-float" id="CC_prevBtn">Back</div>
        </div>
    </div>

</section>
</div>
<div id="RS_resultSec">
    <div class="row">
        <div class="btn" id="RS_resultSecClose" style="float: right">X</div>
    </div>
    <div class="row">
        <div id="RS_resultSecTitle"></div>
    </div>
    <div class="row">
        <div class="col-lg-6 col-sm-6">
            <div id="RS_basicResultChart"></div>
        </div>
        <div class="col-lg-6 col-sm-6">
            <div id="RS_fourierChart"></div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6 col-sm-6">
            <div id="RS_dampingChart"></div>
        </div>
        <div class="col-lg-6 col-sm-6">
            <div id="RS_dampingFactor" class = "result-divs"></div>
            <div id="RS_dampedFrequency" class = "result-divs"></div>
            <div id="RS_naturalFrequency" class = "result-divs"></div>
            <div id="RS_disturbanceTime" class = "result-divs"></div>
        </div>
    </div>
</div>
<div id="CC_ipAddress" style="display: none" value="${ip}"></div>

</body>
</html>
