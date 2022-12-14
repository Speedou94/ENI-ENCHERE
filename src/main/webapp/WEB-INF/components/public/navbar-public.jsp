<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 21/07/2022
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message_jsp" var="message"/>
<c:set var="langue_choisie" value="${param.lang}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav class="navbar is-info" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="${pageContext.request.contextPath}/encheres/">
            <img src="../../img/logo.png" alt="LogoEniEncheres" id="logo" width="128" height="28">
        </a>

        <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample" id="burger">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>

    <div id="navbar-public" class="navbar-menu">
        <div class="navbar-start">



            <div class="navbar-item has-dropdown is-hoverable is-info">
                <p class="navbar-link is-info">
                    <fmt:message key="navbar_public_choix_langue" bundle="${message}"/>
                </p>

                <div class="navbar-dropdown is-info">
                    <a href="${pageContext.request.contextPath}?lang=fr" class="navbar-item is-info">
                        <span> <img class="image is-24x24 mr-4" src="../../img/drapeau_fr.png" alt="drapeau francais"> </span>    <fmt:message key="navbar_public_francais" bundle="${message}"/>
                    </a>

                    <a href="${pageContext.request.contextPath}?lang=en" class="navbar-item is-info">
                        <span> <img class="image is-24x24 mr-4" src="../../img/drapeau-en.png" alt="drapeau anglais"> </span>  <fmt:message key="navbar_public_anglais" bundle="${message}"/>
                    </a>
                    <a></a>

                </div>
            </div>


        </div>

        <div class="navbar-end">
            <div class="navbar-item">
                <div class="buttons">
                    <a class="button is-light is-info" href="${pageContext.request.contextPath}/utilisateurs">
                        <strong><fmt:message key="navbar_public_signIn" bundle="${message}"/></strong>
                    </a>
                    <a class="button is-primary" href="${pageContext.request.contextPath}/login">
                        <fmt:message key="navbar_public_login" bundle="${message}"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</nav>
</body>
</html>
