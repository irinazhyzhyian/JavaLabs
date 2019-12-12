<%--
  Created by IntelliJ IDEA.
  User: qwertyu
  Date: 12.12.2019
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Працівник</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="padding: 20px">
<form method="POST">
    <div class="form-group">
        <label>Id:</label>
        <input name="id" type="number" placeholder="Введіть id" value="${person.id}" class="form-control">
    </div>
    <div class="form-group">
        <label>Ім'я:</label>
        <input name="firstName" type="text" placeholder="Введіть ім'я" value="${person.firstName}" class="form-control">
    </div>
    <div class="form-group">
        <label>Прізвище:</label>
        <input name="lastName" type="text" placeholder="Введіть прізвище" value="${person.lastName}" class="form-control">
    </div>
    <div class="form-group">
        <label>Дата народження:</label>
        <input name="birthday" type="date" placeholder="Введіть дату народження " value="${person.birthday}" class="form-control">
    </div>
    <div class="form-group">
        <label>Заробітня плада:</label>
        <input name="salary" type="text" placeholder="Введіть зп" value="${person.salary}" class="form-control">
    </div>
    <input type="submit" class="btn btn-primary" value="Зберегти">
</form>
</div>
</body>
</html>
