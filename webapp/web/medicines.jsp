<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qwertyu
  Date: 12.12.2019
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Перелік ліків</title>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">--%>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="padding: 20px">
<div class="m-5">
    <h1>Перелік ліків</h1>
    <a href="./add-medicine"><i class="fa fa-plus" aria-hidden="true"></i> Додати медикамент</a>
    <hr>
    <ul>
        <c:forEach items="${medicines}" var="medicine">
            <li><a href="./add-medicine?id=${medicine.id}">${medicine.name}</a></li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
