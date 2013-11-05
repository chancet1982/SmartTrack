<%@page import="java.util.*" %>
<%String str = request.getParameter("UserName");

session.setAttribute("UserName", request.getParameter("UserName"));%>
Welcome to <%= session.getAttribute( "UserName" ) %>
<% if (session.getAttribute("UserName").equals("")){%>
<a href="login.jsp"><b>Login </b></a>
<%}
else{%>
<a href="logout.jsp"><b>Logout</b></a>
<%}
%>