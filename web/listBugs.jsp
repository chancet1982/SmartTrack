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
        <h1>Bugs...</h1>
        <div class="box color-light round-corners shadow padding clearfix">
            <table>
                <thead>
                <tr>
                    <th>Bug Id</th>
                    <th>Bug Title</th>
                    <th>Bug Description</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${bugs}" var="bug">
                    <tr>
                        <td><c:out value="${bug.bugID}" /></td>
                        <td><c:out value="${bug.bugTitle}" /></td>
                        <td><c:out value="${bug.bugDescription}" /></td>
                        <td><a href="BugServlet?action=delete&bugID=<c:out value="${bug.bugID}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
