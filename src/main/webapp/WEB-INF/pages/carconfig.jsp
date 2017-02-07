<%--
  Created by IntelliJ IDEA.
  User: MaN
  Date: 8/12/2016
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>
<script src="<c:url value="/resources/js/carconfig.js" />"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">

<div class="vector-header">

</div>
<div>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
            </div>
            <ul class="nav navbar-nav">
                <li><a href="#">Dashboard</a></li>
                <li><a href="customer">Customer</a></li>
                <li><a href="vehicle">Vehicle</a></li>
                <li class="active" style="
    float: right;
    background-color: '#47759a';
">
                    <a href="new">Create new job</a>
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
<section id="CC_detailSection" class="configsection">
    <div class="container well">
        <div class="row">
            <div class="col-lg-3">
                <div class="label-primary">Customer Name</div>
            </div>
            <div class="col-lg-3"></div>
            <div class="col-lg-3"></div>
            <div class="col-lg-3"></div>
        </div>
    </div>
    <div class="container well">
        <div class="row">
            <div class="col-lg-3">
                <div class="label-primary">Customer Name</div>
            </div>
            <div class="col-lg-3"></div>
            <div class="col-lg-3"></div>
            <div class="col-lg-3"></div>
        </div>
    </div>
    <div class="container well">
        <div class="row">
            <div class="col-lg-3">
                <div class="label-primary">Customer Name</div>
            </div>
            <div class="col-lg-3"></div>
            <div class="col-lg-3"></div>
            <div class="col-lg-3"></div>
        </div>
    </div>
    <div class="row config-nav-buttons">
        <div class="col-lg-12">
            <div class="btn btn-primary right-float" id="CC_nextBtn">Next</div>
        </div>
    </div>

</section>
<section class="configsection" id="CC_carConfigSection" style="display: none">

    <div class="row">
        <div class="vector-car-container col-lg-8">
            <div class="car-config-void-1"></div>
            <div class="device-area text-center" id="carLeftTopWheel">
                <div class="device-container container-not-filled">
                    <div class="device-clear"></div>
                </div>
            </div>
            <div class="device-area" id="carEngine">
                <div class="device-container container-not-filled">
                    <div class="device-clear"></div>
                </div>
            </div>
            <div class="device-area" id="carRightTopWheel">
                <div class="device-container container-not-filled">
                    <div class="device-clear"></div>
                </div>
            </div>
            <div class="car-config-void-2">
            </div>
            <div class="car-config-void-1">
            </div>
            <div class="device-area" id="carLeftBottomWheel">
                <div class="device-container container-not-filled">
                    <div class="device-clear"></div>
                </div>
            </div>
            <div class="device-area" id="carDifferential">
                <div class="device-container container-not-filled">
                    <div class="device-clear"></div>
                </div>
            </div>
            <div class="device-area" id="carRightBottomWheel">
                <div class="device-container container-not-filled">
                    <div class="device-clear"></div>
                </div>
            </div>

        </div>

        <div class="col-lg-4 right-float" id="CC_activeDeviceContainer">
            <div class="vector-settings-bar">
                <div class="vector-btn-close-small"></div>
            </div>
            <div id="CC_activeDeviceInnerContainer">

            </div>
        </div>
    </div>
    <div class="row config-nav-buttons">
        <div class="col-lg-12">
            <div class="btn btn-primary right-float" id="CC_prevBtn">Back</div>
        </div>
    </div>
</section>
</body>
</html>
