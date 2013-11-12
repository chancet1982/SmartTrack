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
            </ul>
        </div>
    </div>
</div>
<div id="site-content">
    <div class="site-width clearfix">
        <h1>Invite user to organization</h1>
        <div class="box color-light round-corners shadow padding">
            <form action="UserServlet" method="POST">
                <ul>
                    <li>
                        <label>Type in E-mail address</label>
                        <input type="text" name="userEmail" class="required">
                    </li>
                    <li>
                        <input type="submit" value="Submit">
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
</body>
</html>