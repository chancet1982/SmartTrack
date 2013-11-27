<%
    System.out.println("making sure there is a cookie");
    String pwd = null, uid = null;
    Cookie cookie = null;
    Cookie[] cookies = null;
    cookies = request.getCookies();
    if( cookies != null )  {
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
        System.out.println("Could not find cookies - must go to login.jsp");
    }
    if (uid == null || pwd == null ) {
        response.sendRedirect("login.jsp");
    }
%>