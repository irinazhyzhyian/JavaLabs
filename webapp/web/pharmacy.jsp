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
</head>
<body>
<h1>Pharmacy</h1>
<h2>Stock</h2>
<ul>
</ul>
<c:forEach items="${countMedicines}" var="countMedicine">
    <a href="./countMedicine?id=${countMedicine.getId()}">${countMedicine.getMedicine().getName()} count: ${countMedicine.getCount()}</a>
    <br>
</c:forEach>

<%--<%= request.getAttribute("pharmacies").toString() %>--%>


<form>
   <%-- <input type="hidden" name="action" id="btnId" value="delete"/>--%>
    <input type="submit" value="delete" onclick="handleDelete()">
</form>
<br>
<script type="text/javascript">
    function handleDelete(clickedId)
    {
        var url = "http://localhost:8000/Gradle___untitled_1_0_SNAPSHOT_war/pharmacy?id=" + clickedId;
        var xhr = new XMLHttpRequest();
        var del = confirm("Do you wanna delete?");
        if (del === true){
            xhr.open("DELETE", url, del);
        }
        xhr.onload = function () {
            document.location.reload();
        };
        xhr.send(null);
    }
    function handleUpdate(clickedId) {
        window.location.href = "http://localhost:8080/parser-resume/address?updateId="+clickedId;
    }
</script>
</body>
</html>
