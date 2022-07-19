<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 17/07/2022
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}" scope="session"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../../style/style.css">
    <title>Accueil</title>
</head>
<body>
<header>
    <%@include file="./../components/public/navbar-public.jspf"%>

</header>
<div class="box">
    <h1 class="has-text-centered title is-1">Liste des enchères</h1>

    <%@include file="./../components/EncherePage/Searchform.jspf"%>
</div>
<div class="enchere-cards">
</div>
<%@include file="./../components/EncherePage/CardEnchere.jspf"%>

<footer>
    <%@include file="./../components/public/footer.jspf"%>
</footer>
</body>
<script src="../../script/nav-bar-public.js"></script>
</html>
