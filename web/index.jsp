<%--
  Created by IntelliJ IDEA.
  User: mn
  Date: 05-11-13
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="includes/documentHead.jsp" %>
  <body>
  <%
      Cookie cookie = null;
      Cookie[] cookies = null;
      // Get an array of Cookies associated with this domain
      cookies = request.getCookies();
      if( cookies != null ){
        validateCookie();
      }else{
          <c:redirect url="login.jsp"/>
      }
  %>
  </body>
</html>