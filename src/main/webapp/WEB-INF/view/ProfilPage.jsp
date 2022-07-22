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
            <th>Pseudo</th>
            <td> ${login.pseudo}</td>

        </tr>
        <tr>
            <th>Nom : </th>
            <td> ${login.nom}</td>

        </tr>
        <tr>
            <th>Prénom</th>
            <td> ${login.prenom}</td>

        </tr>

        <tr>
            <th>Email</th>
            <td> ${login.email}</td>

        </tr>
        <tr>
            <th>Téléphone</th>
            <td> ${login.telephone}</td>

        </tr>
        <tr>
            <th>Rue</th>
            <td> ${login.rue}</td>

        </tr>
        <tr>
            <th>Code postal</th>
            <td> ${login.codePostal}</td>

        </tr>
        <tr>
            <th>Ville</th>
            <td> ${login.ville}</td>

        </tr>
    </table>

<footer>
    <jsp:include page="${pageContext.request.contextPath}/footer"/>
</footer>
</body>
<script src="../../script/nav-bar-public.js"></script>
</html>
