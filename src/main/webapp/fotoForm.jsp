<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="./photoForm">
    <label>Nom:</label>
    <input name="nom">

    <label>Descripció:</label>
    <textarea name="descripcio"></textarea>

    <label>URL:</label>
    <input name="url" type="url">

    <label>Privada?</label>
    <input type="checkbox" name="privada" value="si">

    <input type="submit" value="Enviar">
</form>
</body>
</html>
