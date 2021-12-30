<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.12.2021
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin panel</title>
</head>
<body>
<h1>Hi there</h1>
<div class=folder-table>
    <table>
        <c:forEach var="users" items="${usersFromDB}">
            <tr>
                <td>
                    ${users.key}
                </td>
                <td>
                    ${users.value.name}
                </td>
                <td>
                    ${users.value.role}
                </td>
                <td>
                    <form action="/adminPanel" method="post">
                        <select size="1" name="roleSelect">
                            <option disabled>Select new role</option>
                            <option value="admin">admin</option>
                            <option value="worker">worker</option>
                        </select>
                        <input type="hidden" value="${users.key}" name="userId">
                        <input type="submit" value="Change role">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
