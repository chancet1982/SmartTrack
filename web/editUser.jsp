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
        <h1>Edit User Details</h1>
        <div class="box color-light round-corners shadow padding clearfix">
            <form action="UserServlet" method="POST">
                <input name="userPassword" value="<c:out value="${user.userpassword}"/>" class="hidden" />
                <input name="userID" value="<c:out value="${user.userid}"/>" class="hidden" />
                <ul>
                    <li>
                        <label>User email</label>
                        <input name="userEmail" value="<c:out value="${user.useremail}"/>" class="required email" readonly="true" />
                    </li>
                    <li>
                        <label>User Last Name</label>
                        <input name="firstName" value="<c:out value="${user.firstname}"/>" class="required"/>
                    </li>
                    <li>
                        <label>User Last Name</label>
                        <input name="lastName" value="<c:out value="${user.lastname}"/>" class="required" />
                    </li>
                    <li>
                        <label>Does this user manage projects? (this gives access to the maintain projects panel)</label>
                        <c:if test="${user.ismanager}">
                            <input type="radio" name="ismanager" value="true" checked> Yes
                            <input type="radio" name="ismanager" value="false" > No
                        </c:if>
                        <c:if test="${!user.ismanager}">
                            <input type="radio" name="ismanager" value="true"> Yes
                            <input type="radio" name="ismanager" value="false" checked> No
                        </c:if>
                    </li>
                    <li>
                        <label>Does this user handle bugs? (this gives access to viewing bugs)</label>
                        <c:if test="${user.ishandler}">
                            <input type="radio" name="ishandler" value="true" checked> Yes
                            <input type="radio" name="ishandler" value="false" > No
                        </c:if>
                        <c:if test="${!user.ishandler}">
                            <input type="radio" name="ishandler" value="true"> Yes
                            <input type="radio" name="ishandler" value="false" checked> No
                        </c:if>
                    </li>
                    <li>
                        <label>Does this user report bugs? (this gives access to "add bugs" option)</label>
                        <c:if test="${user.isreporter}">
                            <input type="radio" name="isreporter" value="true" checked> Yes
                            <input type="radio" name="isreporter" value="false" > No
                        </c:if>
                        <c:if test="${!user.isreporter}">
                            <input type="radio" name="isreporter" value="true"> Yes
                            <input type="radio" name="isreporter" value="false" checked> No
                        </c:if>
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
