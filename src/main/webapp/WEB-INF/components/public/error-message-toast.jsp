<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 21/07/2022
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message_error" var="errors"/>
<html>
<head>
    <title>Error-toast</title>
</head>
<body onload="toast()">
<div class="notification is-danger is-top" id="toast">
   <p class="title is-4 has-text-centered"> <strong><fmt:message key="${error}" bundle="${errors}"/></strong></p>
</div>
</body>
<script src="../../../script/taost.js"></script>
</html>
