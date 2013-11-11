<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%
    System.out.println("Im in the index.jsp");
    String pwd = null, uid = null;
    Cookie[] cookies;

    cookies = request.getCookies();
    for (int i = 0; i < cookies.length; i++) {
        if (cookies[i].getName().equals("uid")) {
            System.out.println("there is a UID cookie");
            uid = cookies[i].getValue();
        }
        if (cookies[i].getName().equals("pwd")) {
            System.out.println("there is a PWD cookie");
            pwd = cookies[i].getValue();
        }
    }

    if (uid != null && !uid.isEmpty() && pwd != null && !pwd.isEmpty()) {
        System.out.println("Should validate cookie");
        response.sendRedirect("Login?action='validateCookie'&userName='uid'&userPassword='pwd'");
    } else {
        System.out.println("Should redirect to login");
        response.sendRedirect("Login?action='redirecttologin'");
    }
    %>
