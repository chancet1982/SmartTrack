<%--
  Created by IntelliJ IDEA.
  User: mn
  Date: 05-11-13
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="UserServlet" method="POST">
    <ul>
        <li>
            <label>Username</label>
            <input type="text" name="userName" class="required">
        </li>
        <li>
            <label>First name</label>
            <input type="text" name="firstName" class="required">
        </li>
        <li>
            <label>Last name</label>
            <input type="text" name="lastName" class="required">
        </li>
        <li>
            <label>Type in E-mail address</label>
            <input type="text" name="userEmail" class="required">
        </li>
        <li>
            <label>Type in password</label>
            <input type="text" name="userPassword" class="required">
        </li>
        <li>
            <label>Repeat password</label>
            <input type="password" name="password2" class="required">
        </li>
        <li>
            <input type="submit" value="Submit">
        </li>
    </ul>
</form>
</body>
</html>