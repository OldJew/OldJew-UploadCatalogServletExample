<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
<%--    <div>--%>
<%--        <form action="upload" method="post" enctype="multipart/form-data">--%>
<%--            <input type="text" name="description" />--%>
<%--            <input type="file" name="file" />--%>
<%--            <input type="submit" />--%>
<%--        </form>--%>
<%--    </div>--%>
<div>
    <p><a href="/adminPanel">Admin panel</a></p>
</div>
<div>
    <table>
        <c:forEach items="${filesFromServer}" var="file">
            <tr>
                <td>
                    ${file.name}
                </td>
                <td>
                    <a href="/unload?filename=${file.name}">Download</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div>
    <form action="/signIn" method="post">
        <p><input type="text" id="username" name="username"/><label for="username">Username</label></p>
        <p><input type="password" id="password" name="password"/><label for="password">Password</label></p>
        <input type="submit"/>
    </form>
</div>


</body>
</html>
