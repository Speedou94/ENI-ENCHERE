<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 20/07/2022
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}" scope="session"/>
<fmt:setBundle basename="message_jsp" var="message"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../../style/style.css">
    <title>Profil</title>
</head>
<body>
<header>
    <c:if test="${!empty login}">
        <jsp:include page="${pageContext.request.contextPath}/navbar-private"/>
    </c:if>
</header>

    <table>

        <tr>
            <th><fmt:message key="profil_page_pseudo" bundle="${message}"/></th>
            <td> ${login.pseudo}</td>

        </tr>
        <tr>
            <th><fmt:message key="profil_page_last_name" bundle="${message}"/></th>
            <td> ${login.nom}</td>

        </tr>
        <tr>
            <th><fmt:message key="profil_page_first_name" bundle="${message}"/></th>
            <td> ${login.prenom}</td>

        </tr>

        <tr>
            <th><fmt:message key="profil_page_email" bundle="${message}"/></th>
            <td> ${login.email}</td>

        </tr>
        <tr>
            <th><fmt:message key="profil_page_telephone" bundle="${message}"/></th>
            <td> ${login.telephone}</td>

        </tr>
        <tr>
            <th><fmt:message key="profil_page_street" bundle="${message}"/></th>
            <td> ${login.rue}</td>

        </tr>
        <tr>
            <th><fmt:message key="profil_page_postal_code" bundle="${message}"/></th>
            <td> ${login.codePostal}</td>

        </tr>
        <tr>
            <th><fmt:message key="profil_page_ville" bundle="${message}"/></th>
            <td> ${login.ville}</td>

        </tr>
    </table>
<div class="flex-btn-search">
    <a href="${pageContext.request.contextPath}/profil?edit=true" class="button is-primary is-light"><fmt:message key="profil_page_button_modifier" bundle="${message}"/></a>
<c:if test="${ !empty editProfil}">
<jsp:include page="${pageContext.request.contextPath}/sign-up-form" />
</c:if>
    <!-- mettre une modal pour confirmer par mdp-->
</div>
<div>
    <c:if test="${!empty confirmModal}">
        <jsp:include page="${pageContext.request.contextPath}/confirm-modal"/>
    </c:if>
</div>
<div>
    <c:if test="${!empty newPasswordModal}">
        <jsp:include page="${pageContext.request.contextPath}/change-password"/>
    </c:if>
</div>
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
