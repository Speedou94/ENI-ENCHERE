<%--
  Created by IntelliJ IDEA.
  User: manar
  Date: 18/07/2022
  Time: 23:55
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
    <title><fmt:message key="sign_up_titre_jsp" bundle="${message}"/></title>
</head>
<body>
<header>
    <%@include file="./../components/public/navbar-public.jspf"%>
</header>
<div>
    <h1><fmt:message key="sign_up_header" bundle="${message}"/></h1>
<%@include file="./../components/SignUp/sign-up-form.jspf"%>

</div>
<div>
    <c:if test="${!empty error}">
        <%@include file="./../components/public/error_message_toast.jspf"%>
    </c:if>

</div>
</body>
</html>
