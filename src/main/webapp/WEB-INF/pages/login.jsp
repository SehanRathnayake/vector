<!--<div class="container vector-header">
    <h1>

    </h1>
</div>
-->

<div id="dash_animation" class=" container dash-background animated bounceInLeft " style="
    height:20%; position: relative;width: 90%" >
</div>
<div class="row">


    <div class="col-lg-4"></div>
    <div class="col-lg-4" style="
    margin-top: 50px;
">
        <div class="container col-lg-12 well">
            <h1>Login</h1>
            <c:if test="${param.failed == true}">
                <div>Your login attempt failed. Please try again.</div>
            </c:if>
            </br>
            <form class="main" action="${postLoginUrl}" method="post">
                <div class="row">
                    <div class="col-lg-12">
                        <b>Username</b>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12"><input class="form-control" type="text" name="j_username"/></div>
                    <br/></div>
                <br>
                <div class="row">
                    <div class="col-lg-12">
                        <b>Password</b>
                    </div></div>
                <div class="row">
                    <div class="col-lg-12">
                        <input class="form-control" type="password" name="j_password"/>
                    </div></div>
                <div class="row">
                    <div class="col-lg-12">
                        <input type="checkbox" name="_spring_security_remember_me"/>Remember me
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <br/><input class="btn vector-btn-default col-lg-12" type="submit" value="Log in"/><br/><br/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/js/login.js" />"></script>
</body>
</html>