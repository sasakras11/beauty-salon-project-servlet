<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 31.05.20
  Time: 00:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<table style="width:100%">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>description</th>
        <th>duration(h)</th>

    </tr>
<c:forEach var="procedure" items="${requestScope.procedures}">
    <tr>
        <td>${procedure.name}</td>
        <td>${procedure.description}</td>
        <td>${procedure.durationHours}</td>
       <td><a href="/?procedureId=<c:out value='${procedure.id}&command=ChooseProcedure'/>">choose procedure</a></td>
    </tr>

</c:forEach>
</table>

<table>

    <c:forEach begin="1" end="${requestScope.pagesCount}" varStatus="loop">
       <th><a href="/?page=<c:out value='${loop.index}&command=FindPage'/>">${loop.index}</a></th>

    </c:forEach>
    </table>
</body>
</html>
