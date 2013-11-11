<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%
    System.out.println("Im in the index.jsp");
    String pwd = null, uid = null;
    Cookie[] cookies;

    cookies = request.getCookies();
    if (cookies.length > 0){
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
    } else {
        System.out.println("No Cookies found...");
    }

    if (uid != null && !uid.isEmpty() && pwd != null && !pwd.isEmpty()) {
        response.sendRedirect("Login?action=validateCookie");
    } else {
        response.sendRedirect("login.jsp");
    }
    %>
