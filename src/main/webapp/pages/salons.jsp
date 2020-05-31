<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 29.05.20
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
salons
<c:forEach var="salon" items="${requestScope.salons}">

    <a href="/?salonId=<c:out value='${salon.id}&command=ChooseSalon'/>" >${salon.address}</a>

</c:forEach>
</body>
</html>
