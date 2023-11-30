<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Fotos del jsp 3</h1>
<p>Aqui hi anira el bucle</p>
<p><c:out value="hola mon 2"></c:out></p>
<p><c:out value="${titol}"></c:out></p>
<h2>${titol}</h2>
<p><c:out value="${picture.url}"></c:out></p>
<!-- <img src="${picture.url}" alt="${picture.descripcio}"> -->

<c:forEach items="${pictures}" var="f">
    <c:if test="${not f.privada}">
        <p><img src="${f.url}" alt="${f.descripcio}"></p>
    </c:if>
</c:forEach>
</body>
</html>

