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
    <c:if test="${empty login}">
        <%@include file="./../components/public/navbar-public.jspf"%>
    </c:if>
    <c:if test="${!empty login}">
        <%@include file="./../components/public/navbar-private.jspf"%>
    </c:if>
</header>
<div class="box">
    <h1 class="has-text-centered title is-1"> a mettre dans message_jsp =>Liste des enchères</h1>

    <%@include file="./../components/EncherePage/search-form.jspf"%>
</div>
<div class="enchere-cards">
</div>
<!-- TODO: faire attention au nom des components lower kebab case
<%@include file="./../components/EncherePage/card-encheres.jspf"%>
<div>
    <c:if test="${!empty error}">
        <%@include file="./../components/public/error_message_toast.jspf"%>
    </c:if>

</div>
<footer>
    <%@include file="./../components/public/footer.jspf"%>
</footer>
</body>
<script src="../../script/nav-bar-public.js"></script>
</html>
