<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qwertyu
  Date: 12.12.2019
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Працівники</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="padding: 20px">
<div class="m-5">
    <h1>Перелік працівників</h1>
    <a href="./add-person"><i class="fa fa-plus" aria-hidden="true"></i> Додати працівника</a>
    <hr>
    <ul>
        <c:forEach items="${persons}" var="person">
            <li><a href="./add-person?id=${person.id}">${person.firstName} ${person.lastName}</a></li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
