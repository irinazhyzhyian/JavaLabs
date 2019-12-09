<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> -->

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
</head>
<body>
<h1>Home page </h1>

    <c:forEach items="${pharmacies}" var="pharmacy">
         <h3>
        <a href="pharmacy?id=${pharmacy.getId()}">${pharmacy.getName()}</a>

         </h3>

        <li>
        <a href="person?id=${pharmacy.getPharmacist().getId()}"> Pharmacist: ${pharmacy.getPharmacist().getFirstName()} ${pharmacy.getPharmacist().getLastName()}</a>
        </li>
        <br>
    </c:forEach>
</body>
</html>
