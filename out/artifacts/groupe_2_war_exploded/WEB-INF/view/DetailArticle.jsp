<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 19/07/2022
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

</header>

<div>
    <c:if test="${!empty error}">
        <%@include file="./../components/public/error_message_toast.jspf"%>
    </c:if>

</div>

</body>
</html>
