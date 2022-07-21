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
<body>
<div class="notification is-danger">
    <button class="delete"></button>

    <strong><fmt:message key="${error}" bundle="${errors}"/></strong>
</div>
</body>
</html>
