<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 19/07/2022
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${param.lang}" scope="session"/>
<fmt:setBundle basename="message_jsp" var="message"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../../style/style.css">
    <title>Title</title>
</head>
<body>
<header>
    <c:if test="${!empty login}">
        <jsp:include page="${pageContext.request.contextPath}/navbar-private"/>
    </c:if>
</header>
<jsp:include page="${pageContext.request.contextPath}/nouvel-article"/>
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
