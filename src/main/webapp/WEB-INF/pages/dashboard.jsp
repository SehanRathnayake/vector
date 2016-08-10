<%@ include file="header.jsp" %>
<script src="https://cdn.crunchify.com/wp-content/uploads/code/jquery.sparkline.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="<c:url value="/resources/js/dashboard.js" />"></script>

<div class="block">
    Hi, <security:authentication property="principal.fullname"/>.
    <a href="${logoutUrl}">Log out</a>
    <h1>
        Dashboard
    </h1>
</div>
<!--sample graph | use SG_ as id prefix-->
<div class = "block-graph" align="center" name = "Sample Graph">

    <div id="SG_result"></div>

    <br>
    <br> One: &nbsp;&nbsp;&nbsp;&nbsp;<span id="one">.</span>
    <br>
    <br> Two: &nbsp;&nbsp;&nbsp;&nbsp;<span id="two">.</span>
    <br>
    <br> Three: &nbsp;&nbsp;&nbsp;&nbsp;<span id="three">.</span>
    <br>
    <br> <br> <br>


</div>

<div class="block-graph" name = "highChart">

    <div id="HCG_container" style="width: 550px; height: 400px; margin: 0 auto"></div>

</div>
</body>
</html>