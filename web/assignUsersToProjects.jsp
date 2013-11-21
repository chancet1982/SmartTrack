<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
        <h1>Assign users to projects</h1>
        <div class="box color-light round-corners shadow padding" style="padding:8px;">
            <h2>users</h2>
                <ul class="users">

                    <c:forEach items="${users}" var="user">
                        <li class="user ico">
                            <span class="icon user"></span><c:out value="${user.firstname}" />
                        </li>
                    </c:forEach>
                </ul>
            <h2>Projects</h2>
                <ul class="projects">
                    <c:forEach items="${projects}" var="project">
                        <li class="project"><c:out value="${project.projectName}" /></li>
                    </c:forEach>
                </ul>
        </div>
    </div>
</div>
</body>
</html>