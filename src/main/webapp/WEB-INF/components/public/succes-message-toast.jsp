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
<div class="notification is-primary" id="toast">
    <strong><fmt:message key="${succes}" bundle="${message}"/></strong>
</div>
</body>
<script src="../../../script/taost.js"></script>
</html>
