<%@include file="includes/validateCookie.jsp" %>
<%@include file="includes/documentHead.jsp" %>

<body>
<div id="site-header">
    <div class="site-width clearfix">
        <div id="site-logo"></div>
        <div id="site-navigation" class="clearfix">
            <%@include file="includes/navigation.jsp" %>
        </div>
    </div>
</div>
<div id="site-content">
    <div class="site-width clearfix">
        <h1>Welcome to Smarttrack</h1>
        <div class="box color-light round-corners shadow padding" style="padding:8px;">
            <p>What would you like to do next?</p>
            <a href="inviteUsers.jsp" class="button">Invite Users</a>
            <a href="UserServlet?action=ListUsers" class="button">Maintain Users</a>
            <a href="createProject.jsp" class="button">Create Projects</a>
            <a href="ProjectServlet?action=ListProjects" class="button">Maintain Projects</a>
        </div>
    </div>
</div>
</body>
</html>