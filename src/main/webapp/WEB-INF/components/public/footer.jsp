<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 21/07/2022
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message_jsp" var="message"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fmt:setBundle basename="message_jsp" var="message"/>

<footer class="footer">
    <p class="has-text-centered"><fmt:message key="footer_credit" bundle="${message}"/></p>
</footer>
</body>
</html>
