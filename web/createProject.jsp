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
        <h1>Create new user</h1>
        <div class="box color-light round-corners shadow padding">
            <form action="ProjectServlet" method="POST">
                <ul>
                    <li>
                        <label>Project Name</label>
                        <input type="text" name="projectName" class="required">
                    </li>
                    <li>
                        <label>Project Version</label>
                        <input type="text" name="projectVersion" class="required">
                    </li>
                    <li>
                        <input type="submit" value="Submit">
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>

<%@include file="includes/footerMessage.jsp" %>
</body>
</html>