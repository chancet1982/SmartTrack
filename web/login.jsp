<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="includes/document-head.jsp" %>

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
        <h1>Main Headline</h1>
        <div class="box color-1 round-corners shadow padding" style="padding:8px;">
            <form method="POST" action="sessionLogin.jsp">
            <ul>
                <li><label>Username</label></li>
                    <li><input type="text" name="UserName" class="required"></li>
                <li><label>Password</label></li>
                    <li><input type="password" name="Password" class="required"></li>
                <li><input type="submit" value="Submit"></li>
                <li><a href="#">Create new user</a></li>
                <li><a href="#">Forgot Password</a></li>
            </ul>
            </form>
        </div>
    </div>
</div>

<div id="site-footer">
    <div class="site-width clearfix">
        <p>Testing LESS, Copyright (c) 2013</p>
    </div>
</div>
</body>
</html>