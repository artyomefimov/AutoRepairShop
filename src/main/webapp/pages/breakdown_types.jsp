<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 777
  Date: 12.09.2019
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Types</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
<c:forEach items="${list}" var="type">
    <tr>
        <td>${type.id}</td>
        <td>${type.name}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
