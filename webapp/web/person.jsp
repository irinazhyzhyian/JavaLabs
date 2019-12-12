<%--
  Created by IntelliJ IDEA.
  User: qwertyu
  Date: 05.12.2019
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pharmacist</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">

</head>
<body style="padding: 20px">
<div>
    <h1>Фармацевт</h1>
    <table border="2">
        <tr>
            <th>Iм'я </th>
            <td>${person.getFirstName()}</td>
        </tr>
        <tr>
            <th>Прізвище </th>
            <td>${person.getLastName()}</td>
        </tr>
        <tr>
            <th>Дата народження </th>
            <td>${person.getBirthday()}</td>
        </tr>
        <tr>
            <th>Заробітня плата</th>
            <td> ${person.getSalary()}</td>
        </tr>
    </table>
</div>
<br>
<form method="post">
    <button value="deletePharmacy" class="btn btn-primary">Видалити працівника</button>
</form>
</body>
</html>
