<script src="https://cdn.crunchify.com/wp-content/uploads/code/jquery.sparkline.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="<c:url value="/resources/js/graphwidget.js" />"></script>
<script src="<c:url value="/resources/js/dashboard.js" />"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
<div>
    <nav class="navbar navbar-fixed-top">
        <div class="container-fluid" style="
     background-color: #47759a;
    ">
            <ul class="nav navbar-nav" style="color:whitesmoke" >
                <li><a id="dash-animation" class="dash-background animated shake ">
                    <font style="opacity: 0;">ssadsfs</font> </a></li>
                <li cxlass="active"><a href="#" class="color-me" style=" margin-left: 10px">Dashboard</a></li>
                <li><a href="customer" >Customer</a></li>
                <li><a href="vehicle">Vehicle</a></li>
            </ul>
            <a href="${logoutUrl}" style="
    float: right;
    font-size: 15px;
    margin-right: 10px;
    color: whitesmoke;
    margin-top: 15px;
    background-position: center;
            ">Log out</a>
        </div>
    </nav>
</div>

<!--

<div class="vector-header" style="
font-size: 30px;
font-family: inherit;
line-height: 2;
padding: 8px;
color: white;
">

Hi, <security:authentication property="principal.fullname"/>.

</div>
-->
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
<!--
<div id="dash_animation" class="dash-background animated bounceInLeft " style="height:40%" >
</div>

-->

<div>
    <button class="btn btn_job animated pulse" style="position:absolute;line-height: 50%; z-index: 100;top: 42%;left: 42%"
            onclick="window.location.href='new'">CREAT A NEW JOB
    </button>
    <div>

        <ul class="slides">
            <input type="radio" name="radio-btn" id="img-1" checked/>
            <li class="slide-container">

                <div class="slide ">
                    <img src="/vector/resources/img/ins1.jpg"/>
                    <div class="content row  ">
                        <h1  class="asa"  style="   top: 59%;left: 5%; font-size: 50pt">MOUNT</h1>
                        <h3  class="asa"  style="   top: 70%;left: 5%; font-size: 35pt">SENSOR DEVICE</h3>
                        <h5  class="asa"  style="   top: 78%;left: 5%; font-size: 45pt">on THE AXLE</h5>
                    </div>
                </div>
                <div class="nav">
                    <label for="img-5" class="prev">&#x2039;</label>
                    <label for="img-2" class="next">&#x203a;</label>
                </div>
            </li>

            <input type="radio" name="radio-btn" id="img-2"/>
            <li class="slide-container">
                <div class="slide">
                    <img src="/vector/resources/img/ins2.jpg"/>
                    <div class="content row  ">
                        <h1  class="asa"  style="   top: 59%;left: 5%; font-size: 50pt">MOUNT</h1>
                        <h3  class="asa"  style="   top: 70%;left: 5%; font-size: 35pt">another SENSOR DEVICE</h3>
                        <h5  class="asa"  style="   top: 78%;left: 5%; font-size: 45pt">on THE CHASSIS</h5>
                    </div>
                </div>

                <div class="nav">
                    <label for="img-1" class="prev">&#x2039;</label>
                    <label for="img-3" class="next">&#x203a;</label>
                </div>
            </li>

            <input type="radio" name="radio-btn" id="img-3"/>
            <li class="slide-container">
                <div class="slide">
                    <img src="/vector/resources/img/ins3.jpg"/>
                    <div class="content row  ">
                        <h1  class="asa"  style="   top: 65%;left: 5%; font-size: 50pt">TAKE</h1>
                        <h3  class="asa"  style="   top: 76%;left: 5%; font-size: 35pt">THE WHEEL onto a</h3>
                        <h5  class="asa"  style="   top: 84%;left: 5%; font-size: 45pt">HIGHER ELEVATION</h5>
                    </div>
                </div>
                <div class="nav">
                    <label for="img-2" class="prev">&#x2039;</label>
                    <label for="img-4" class="next">&#x203a;</label>
                </div>
            </li>


            <input type="radio" name="radio-btn" id="img-4"/>
            <li class="slide-container">
                <div class="slide">
                    <img src="/vector/resources/img/ins4.jpg"/>
                    <div class="content row  ">
                        <h1  class="asa"  style="   top: 59%;left: 5%; font-size: 50pt">WAIT</h1>
                        <h3  class="asa"  style="   top: 70%;left: 5%; font-size: 35pt">for about 10s & PUSH</h3>
                        <h5  class="asa"  style="   top: 78%;left: 5%; font-size: 45pt">THE VEHICLE</h5>
                    </div>
                </div>
                <div class="nav">
                    <label for="img-3" class="prev">&#x2039;</label>
                    <label for="img-5" class="next">&#x203a;</label>
                </div>
            </li>


            <input type="radio" name="radio-btn" id="img-5"/>
            <li class="slide-container">
                <div class="slide">
                    <img src="/vector/resources/img/ins5.jpg"/>
                    <div class="content row  ">
                        <h1  class="asa"  style="   top: 59%;left: 5%; font-size: 50pt">YOU CAN SEE</h1>
                        <h3  class="asa"  style="   top: 70%;left: 5%; font-size: 35pt">THE RESULTS</h3>
                        <h5  class="asa"  style="   top: 78%;left: 5%; font-size: 45pt">from THE APP</h5>
                    </div>
                </div>
                <div class="nav">
                    <label for="img-4" class="prev">&#x2039;</label>
                    <label for="img-1" class="next">&#x203a;</label>
                </div>
            </li>




            <li class="nav-dots">
                <label for="img-1" class="nav-dot" id="img-dot-1"></label>
                <label for="img-2" class="nav-dot" id="img-dot-2"></label>
                <label for="img-3" class="nav-dot" id="img-dot-3"></label>
                <label for="img-4" class="nav-dot" id="img-dot-4"></label>
                <label for="img-5" class="nav-dot" id="img-dot-5"></label>
            </li>
        </ul>
    </div>
</div>