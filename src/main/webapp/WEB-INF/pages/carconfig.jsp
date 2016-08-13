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
<div class="vector-car-container">
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
<div id="CC_activeDeviceContainer">
    <div class="vector-settings-bar">
        <div class="vector-btn-close-small"></div>
    </div>
    <div id="CC_activeDeviceInnerContainer">

    </div>
</div>
</body>
</html>
