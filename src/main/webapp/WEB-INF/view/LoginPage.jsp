<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 18/07/2022
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message_jsp" var="message"/>
<fmt:setLocale value="${param.lang}" scope="session"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../../style/style.css">
    <title>Login</title>
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/navbar-public"/>
</header>
<div class="box form-login mx-auto">
    <form action="${pageContext.request.contextPath}/login" method="post">
       <label for="pseudo">
           <fmt:message key="login_page_pseudo" bundle="${message}"/>
       </label>
        <input class="input" type="text" placeholder="Text input" id="pseudo" name="pseudo">
        <label for="password">
            <fmt:message key="login_page_password" bundle="${message}"/>
        </label>
        <input class="input" type="password" placeholder="Text input" id="password" name="password">
<div class="btn-form-login">
       <div>
           <button class="button is-info " type="submit">
               <fmt:message key="login_page_btn_connect" bundle="${message}"/>
           </button>
       </div>
        <div class="options-form">
            <label class="checkbox">
                <input type="checkbox">
                <fmt:message key="login_page_check_remember" bundle="${message}"/>
            </label>
            <a class="mt-1">
                <fmt:message key="login_page_forgot_password" bundle="${message}"/>
            </a>
        </div>
</div>

    </form>
    <div>
        <c:if test="${!empty error}">
            <jsp:include page="${pageContext.request.contextPath}/error-toast"/>
        </c:if>
    </div>
    <footer>
        <jsp:include page="${pageContext.request.contextPath}/footer"/>
    </footer>
</div>
</body>
<script src="../../script/nav-bar-public.js"></script>
</html>
