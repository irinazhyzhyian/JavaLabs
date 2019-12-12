<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qwertyu
  Date: 12.12.2019
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Лікарський препарат</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


    <form method="POST">
        <div class="form-group">
            <label>Id:</label>
            <input name="id" type="number" placeholder="Введіть id" value="${medicine.id}" class="form-control">
        </div>
        <div class="form-group">
            <label>Назва:</label>
            <input name="name" type="text" placeholder="Введіть назву" value="${medicine.name}" class="form-control">
        </div>
        <div class="form-group">
            <label>Ціна:</label>
            <input name="price" type="text" placeholder="Введіть ціну" value="${medicine.price}" class="form-control">
        </div>
        <div class="form-group">
            <label>Дата придатності:</label>
            <input name="overdueDate" type="date" placeholder="Введіть дату " value="${medicine.overdueDay}" class="form-control">
        </div>
        <div class="form-group">
            <label>Форма лікарського засобу:</label>
            <input name="form" type="text" placeholder="Введіть форму" value="${medicine.form}" class="form-control">
        </div>
        <input type="submit" class="btn btn-primary" value="Зберегти">
    </form>
</div>
</body>
</html>
