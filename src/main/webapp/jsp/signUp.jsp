<%@ page import="ru.oldjew.servlets.SignUpServlet" %>
<%@ page import="ru.oldjew.repositories.UsersRepositoryImpl" %>
<%@ page import="java.io.Writer" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.12.2021
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
<div>
    <form action="/signUp" method="post">
        <p><input type="text" id ="username" name="username" /><label for="username">Username</label></p>
        <p><input type="password" id = "password" name="password" /><label for="password">Password</label></p>
        <input type="submit" />
    </form>
</div>
<div>
    <% if (request.getAttribute("note")!= null) {
        out.println(request.getAttribute("note"));
    }
    %>
    <a href="/home"> return to homepage</a>
</div>
</body>
</html>
