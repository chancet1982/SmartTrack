<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
        <div class="sidebar right toggleable collapsed clearfix ">
            <div class="toggle collapse"><span class="icon"></span></div>
            <h1>Sidebar</h1>
        </div>
        <h1>Login to our system</h1>
        <div class="box color-light round-corners shadow padding" style="padding:8px;">
            <div class="items-row cols-2">
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
                    <h2>Dont have an Account?</h2>
                    <a href="createUser.jsp">Create new user</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="includes/documentFooter.jsp" %>
</body>
</html>