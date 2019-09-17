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
        <th>INN</th>
        <th>Name</th>
        <th>Address</th>
        <th>Open hours</th>
        <th>Close hours</th>
        <th>Owner name</th>
    </tr>
<c:forEach items="${workshop_list}" var="workshop">
    <tr>
        <td>${workshop.inn}</td>
        <td>${workshop.name}</td>
        <td>${workshop.address}</td>
        <td>${workshop.openHours}</td>
        <td>${workshop.closeHours}</td>
        <td>${workshop.ownerName}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
