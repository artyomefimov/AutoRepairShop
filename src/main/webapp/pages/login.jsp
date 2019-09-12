<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Start page</title>
</head>

<body>

<div class="container" style="width: 300px;">
    <%--<c:url value="/j_spring_security_check" var="loginUrl" />--%>
    <form action="/login" method="get">
        <h2>Please sign in</h2>
        <input type="text" name="j_username" placeholder="Email address" required autofocus value="1">
        <input type="password" name="j_password" placeholder="Password" required value="1">
        <button type="submit">Войти</button>
    </form>
</div>

</body>
</html>
