<%--
  Created by IntelliJ IDEA.
  User: qwertyu
  Date: 03.12.2019
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pharmacy</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body style="padding: 20px">
<h1>${pharmacy.name}</h1>
<h2>В аптеці наявні такі ліки:</h2>

<table CELLPADDING="5" border>
    <tr>
        <th>Назва медикаменту</th>
        <th>Кількість</th>
    </tr>
    <c:forEach items="${countMedicines}" var="countMedicine">
        <tr>
            <td><a href="./countMedicine?id=${countMedicine.id}">${countMedicine.medicine.name}</a></td>
            <td>${countMedicine.count}</td>
        </tr>
    </c:forEach>
</table>
<br>
<form method="post">
    <button value="deletePharmacy" class="btn btn-primary">Видалити аптеку</button>
</form>

</body>
</html>
