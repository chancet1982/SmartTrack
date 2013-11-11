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
            <form action="UserServlet" method="POST">
                <ul>
                    <li>
                        <label>First name</label>
                        <input type="text" name="firstName" class="required">
                    </li>
                    <li>
                        <label>Last name</label>
                        <input type="text" name="lastName" class="required">
                    </li>
                    <li>
                        <label>Type in E-mail address</label>
                        <input type="text" name="userEmail" class="required">
                    </li>
                    <li>
                        <label>Type in password</label>
                        <input type="password" name="userPassword" class="required">
                    </li>
                    <li>
                        <label>Repeat password</label>
                        <input type="password" name="password2" class="required">
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