<div>
    <h1>
        Login Page
    </h1>
</div>

<div>
<c:if test="${param.failed == true}">
    <div>Your login attempt failed. Please try again.</div>
</c:if>
<form class="main" action="${postLoginUrl}" method="post">
    Username: <input type="text" name="j_username"/><br/>
    Password: <input type="password" name="j_password"/><br/>
    <input type="checkbox" name="_spring_security_remember_me"/>
    Remember me<br/>
    <input type="submit" value="Log in"/>
</form>
</div>