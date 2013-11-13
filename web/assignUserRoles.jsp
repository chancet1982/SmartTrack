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
        <h1>User Management</h1>
        <div class="box color-light round-corners shadow padding clearfix">
            <table>
                <thead>
                <tr>
                    <th>User Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><c:out value="${user.userid}" /></td>
                        <td><c:out value="${user.firstname}" /></td>
                        <td><c:out value="${user.lastname}" /></td>
                        <td><c:out value="${user.useremail}" /></td>
                        <td><a href="UserServlet?action=edit&userId=<c:out value="${user.userid}"/>">Edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>