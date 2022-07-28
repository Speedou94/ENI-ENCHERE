<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 21/07/2022
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message_succes" var="message"/>
<html>
<head>
    <title>SuccesMessage</title>
</head>
<body onload="toast()">
<div class="notification is-primary is-top" id="toast">
   <p class="has-text-centered"><strong><fmt:message key="${succes}" bundle="${message}"/></strong></p>
    <c:if test="${!empty totalGagne}">
        <p class="has-text-centered">${totalGagne}</p>
    </c:if>
</div>
</body>
<script src="../../../script/taost.js"></script>
</html>
