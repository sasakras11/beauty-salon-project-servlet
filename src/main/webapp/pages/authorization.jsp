<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="start.css">
    <title>Start</title>
</head>


<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ua" ${language == 'ua' ? 'selected' : ''}>Ukrainian</option>
    </select>
</form>
<div id="login">
    <div class="container" style="margin-top: 160px">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="/?command=Authorization" method="post">
                        <div class="form-group">
                            <label class="text-info"> <fmt:message key="label.username"/></label><br>
                            <input type="text" name="username" id="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="text-info"><fmt:message key="label.password"/></label><br>
                            <input type="text" name="password" id="password" class="form-control">
                        </div>
                        <div class="form-group">

                            <input type="submit" name="loggingIn" id="loggingIn"
                                   value="<fmt:message key="label.login"/>" class="btn btn-info btn-md">
                            <input type="submit" name="registration" id="registration"
                                   value="<fmt:message key="label.register"/>"
                                   class="btn btn-info btn-md">

                        </div>

                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
