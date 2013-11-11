<%@include file="includes/documentHead.jsp" %>

<body>
<div id="site-header">
    <div class="site-width clearfix">
        <div id="site-logo"></div>
        <div id="site-navigation" class="clearfix">
        </div>
    </div>
</div>
<div id="site-content">
    <div class="site-width clearfix">
        <h1>Login to our system</h1>
        <div class="box color-light round-corners shadow padding">
            <div class="items-row cols-2 clearfix">
                <div class="item column-1">
                    <h2>Already have an Account?</h2>
                    <form method="POST" action="Login">
                        <ul>
                            <li><label>Username</label></li>
                            <li><input type="text" name="userEmail" class="required"></li>
                            <li><label>Password</label></li>
                            <li><input type="password" name="userPassword" class="required"></li>
                            <li><input type="submit" value="Submit"></li>
                        </ul>
                    </form>
                </div>
                <div class="item column-2">
                    <h2>Don't have an Account?</h2>
                    <a href="createUser.jsp" class="button round-corners">Create new user</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="includes/documentFooter.jsp" %>
</body>
</html>