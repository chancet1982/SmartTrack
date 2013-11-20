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
        <h1>Invite users to organization</h1>
        <div class="box color-light round-corners shadow padding">
            <form action="MailServlet" method="GET">
                <h2>Invite one or more users to the organization</h2>
                <ul>
                    <li>
                        <label>Type one or more e-mail addresses. Multiple addresses should be separated using a comma Example:mail1@mail.com, mail2@mail.com</label>
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