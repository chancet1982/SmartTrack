<%
    boolean isReporter=false, isHandler=false, isManager=false;
    Cookie[] cs = request.getCookies();
    for (int i = 0; i < cookies.length; i++){
        Cookie c = cs[i];
        if(c.getName().equals("ir") && c.getValue().equals("1")){ isReporter=true; }
        if(c.getName().equals("im") && c.getValue().equals("1")){ isManager=true; }
        if(c.getName().equals("ih") && c.getValue().equals("1")){ isHandler=true; }
    }
%>
<ul>
    <li id="1"><a href="afterLogin.jsp" class="ico"><span class="icon home"></span>Home</a></li>
    <% if(isManager == true){%>
    <li id="2"><a href="UserServlet?action=ListUsers" class="ico"><span class="icon users"></span> Users</a></li>
    <%}%>
    <li id="3"><a href="ProjectServlet?action=ListProjects" class="ico"><span class="icon projects"></span>Projects</a></li>
    <li id="4"><a href="BugServlet?action=ListBugs" class="ico"><span class="icon bugs"></span>Bugs</a></li>
    <li id="4"><a href="BugServlet?action=ListMyBugs" class="ico"><span class="icon bugs"></span>My Bugs</a></li>
    <% if(isManager == true){%>
    <li id="5"><a href="ProjectServlet?action=assignProjects" class="ico"><span class="icon project"></span>Assign Users to Project</a></li>
    <%}%>
    <li id="7"><a href="UserServlet?action=LogoutUser" class="ico"><span class="icon logout"></span>Logout</a></li>
</ul>



