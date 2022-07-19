<%--
  Created by IntelliJ IDEA.
  User: manar
  Date: 18/07/2022
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}" scope="session"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../../style/style.css">
    <title>MonProfil</title>
</head>
<body>
<header>
    <%@include file="./../components/public/navbar-public.jspf"%>
</header>
<div>
    <h1> Mon profil</h1>
<%@include file="./../components/SignUp/sign-up-form.jspf"%>
</div>
</body>
</html>
