<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${error != null}">
    <p>ERROR: ${error}</p>
</c:if>
<form action="login" method="post">
    <label for="usuari">Username</label>
    <input id="usuari" type="text" name="user" placeholder="marian">
    <label>
        Password
        <input type="password" name="password" placeholder="1234">
    </label>
    <input type="submit" value="Entrar">
</form>
</body>
</html>
