<%--
  Created by IntelliJ IDEA.
  User: qwertyu
  Date: 05.12.2019
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>medicine</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">

</head>
<body style="padding: 20px">
<div>
<h1>Лікарський препарат</h1>
    <table border="2">
   <tr>
       <th>Назва: </th>
       <td>${medicine.getName()}</td>
   </tr>
   <tr>
       <th>Лікарська форма: </th>
       <td>${medicine.getForm()}</td>
   </tr>
    <tr>
        <th>Придатний до: </th>
        <td>${medicine.getOverdueDay()}</td>
    </tr>
    <tr>
        <th>Ціна: </th>
        <td>${medicine.getPrice()}</td>
    </tr>
    </table>
</div>
<br>
<form method="post">
    <button value="deleteMedicine" class="btn btn-primary">Видалити медикамент</button>
</form>
</body>
</html>
