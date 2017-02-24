<script src="https://cdn.crunchify.com/wp-content/uploads/code/jquery.sparkline.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="<c:url value="/resources/js/graphwidget.js" />"></script>
<script src="<c:url value="/resources/js/dashboard.js" />"></script>
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

<!--sample graph | use SG_ as id prefix-->

<%--<div class = "block-graph" align="center" name = "Sample Graph">

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

</div>--%>

<div class="block-graph" name="highChart">

    <div id="DB_container_A001"></div>

</div>

<div class="block-graph" name="highChart">

    <div id="DB_container_A002"></div>

</div>
<div class="block-graph" name="highChart">

    <div id="DB_container_A003"></div>

</div>
<div class="block-graph" name="highChart">

    <div id="DB_container_A004"></div>

</div>
<div class="block-graph" name="highChart">

    <div id="DB_container_A005"></div>

</div>


