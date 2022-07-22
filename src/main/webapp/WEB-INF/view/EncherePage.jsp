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
<fmt:setBundle basename="message_jsp" var="message"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../../style/style.css">
    <title><fmt:message key="enchere_page_titre_jsp" bundle="${message}"/></title>
</head>
<body>
<header>
    <c:if test="${empty login}">
       <jsp:include page="${pageContext.request.contextPath}/navbar-public"/>
    </c:if>
    <c:if test="${!empty login}">
        <jsp:include page="${pageContext.request.contextPath}/navbar-private"/>
    </c:if>
</header>
<div class="box">
    <h1 class="has-text-centered title is-1">
        <fmt:message key="enchere_page_liste_enchere" bundle="${message}"/>
    </h1>
    <c:if test="${empty login}">
        <jsp:include page="${pageContext.request.contextPath}/search-article"/>
    </c:if>
    <c:if test="${!empty login}">
        <jsp:include page="${pageContext.request.contextPath}/search-connected"/>
    </c:if>
    <jsp:include page="${pageContext.request.contextPath}/card-article"/>
</div>
<div>
    <c:if test="${!empty error}">
       <jsp:include page="${pageContext.request.contextPath}/error-toast"/>
    </c:if>
</div>
<footer>
   <jsp:include page="${pageContext.request.contextPath}/footer"/>
</footer>
</body>
<script src="../../script/nav-bar-public.js"></script>
</html>
