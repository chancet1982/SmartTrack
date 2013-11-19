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
        <h1>Create new user</h1>
        <div class="box color-light round-corners shadow padding">
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
                        <input type="text" name="userEmail" data-servlet="/UserServlet?action=verifyUnique" class="required unique invalid email">
                        <span class="message box error hidden">Email is already registered and must be unique</span>
                    </li>
                    <li>
                        <label>Type in password</label>
                        <input type="password" name="userPassword" class="required password">
                    </li>
                    <li>
                        <label>Repeat password</label>
                        <input type="password" name="password2" class="required repeat-password">
                    </li>
                    <li>
                        <label>Type in your company name</label>
                        <input type="text" name="companyName" data-servlet="/UserServlet?action=verifyUnique" class="required unique invalid">
                        <span class="message box error hidden">Company already exists, you cannot add a user to an existing company</span>
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