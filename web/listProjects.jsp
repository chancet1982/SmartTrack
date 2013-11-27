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
        <h1>Project Management</h1>
        <div class="box color-light round-corners shadow padding clearfix">
            <table>
                <thead>
                <tr>
                    <th>Project Id</th>
                    <th>Project Name</th>
                    <th>Project Version</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${projects}" var="project">
                    <tr>
                        <td><c:out value="${project.projectID}" /></td>
                        <td><c:out value="${project.projectName}" /></td>
                        <td><c:out value="${project.projectVersion}" /></td>
                        <td><a href="ProjectServlet?action=delete&projectId=<c:out value="${project.projectID}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
