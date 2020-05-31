<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 30.05.20
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Title</title>
</head>
<body>
masters
<c:forEach var="master" items="${requestScope.masters}">

    <a href="/?masterId=<c:out value='${master.id}&command=ChooseMaster'/>">${master.username} </a>

    </c:forEach>
</body>
</html>
