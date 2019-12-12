<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<%--
  Created by IntelliJ IDEA.
  User: qwertyu
  Date: 03.12.2019
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">--%>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="padding: 20px">
<h1>Головна сторінка </h1>

    <c:forEach items="${pharmacies}" var="pharmacy">
        <div>
         <h3>
            <a href="pharmacy?id=${pharmacy.getId()}">${pharmacy.getName()}</a>
         </h3>
            <ul>
                <li>
                    <a href="person?id=${pharmacy.getPharmacist().getId()}"> Pharmacist: ${pharmacy.getPharmacist().getFirstName()} ${pharmacy.getPharmacist().getLastName()}</a>
                </li>
            </ul>
        </div>
    </c:forEach>
<br>
<hr>
<br>
<div>
    <a href="workWithDB.jsp"><i class="fa fa-server" aria-hidden="true"></i> Work with DB</a>
</div>
</body>
</html>
