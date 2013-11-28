<%@include file="includes/validateCookie.jsp" %>
<%@include file="includes/documentHead.jsp" %>

<body>
<style>

</style>
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
        <h1>Assign users to bugs</h1>
        <div class="box color-light round-corners shadow padding" style="padding:8px;">
            <h2>users</h2>
                <ul class="users">

                    <c:forEach items="${users}" var="user">
                        <li class="user ico" data-user-id="${user.userid}" >
                            <span class="icon user"></span><c:out value="${user.firstname}" />
                        </li>
                    </c:forEach>
                </ul>
            <h2>Bugs</h2>
                <ul class="projects">
                    <c:forEach items="${projects}" var="project">
                        <li class="project clearfix" data-project-id='<c:out value="${bug.bugID}"/>' >
                            <span class="project-name"><c:out value="${bug.bugTitle}" /></span>
                            <span class="project-users">
                                <ul></ul>
                            </span>
                        </li>
                    </c:forEach>
                </ul>
        </div>
    </div>
</div>
</body>
</html>