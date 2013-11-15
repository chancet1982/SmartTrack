<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="includes/documentHead.jsp" %>

<body>
<div id="site-header">
    <div class="site-width clearfix">
        <div id="site-logo"></div>
        <div id="site-navigation" class="clearfix">
            <ul>
                <li><a href="afterLogin.jsp">Home</a></li>
                <li><a href="UserServlet?action=ListUsers">Maintain Users</a></li>
                <li><a href="ProjectServlet?action=ListProjects">Maintain Projects</a></li>
                <li><a href="Mail?to=chancet1982@gmail.com&companyName=Samlines">Send Mail</a></li>

            </ul>
        </div>
    </div>
</div>
<div id="site-content">
    <div class="site-width clearfix">
        <div class="sidebar right toggleable collapsed clearfix">
            <div class="toggle collapse"><span class="icon"></span></div>
            <h1>Sidebar</h1>
        </div>
        <h1>You are now logged in</h1>
        <div class="box color-light round-corners shadow padding" style="padding:8px;">
            <p>What would you like to do next?</p>
        </div>
    </div>
</div>
</body>
</html>