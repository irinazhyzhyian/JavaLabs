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
</head>
<body>
<h1>Medicine</h1>
   <p>Name: ${medicine.getName()}</p>
    <br>
   <p>Form: ${medicine.getForm()}</p>
    <br>
    <p>Overdue day: ${medicine.getOverdueDay()}</p>
    <br>
    <p>Price: ${medicine.getPrice()}</p>
</body>
</html>
