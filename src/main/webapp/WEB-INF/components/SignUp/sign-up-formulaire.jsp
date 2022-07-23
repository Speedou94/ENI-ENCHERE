<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 21/07/2022
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="message_jsp" var="message"/>
<html>
<head>
    <title><fmt:message key="sign-up-sign_up" bundle="${message}"/></title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/utilisateurs/">
    <div class="form-new-user">
        <div class="input-container">
            <label> <fmt:message key="sign_up_pseudo" bundle="${message}"/>
                <input class="input" type="text" placeholder="pseudo" name="pseudo" value="${login.pseudo}">
            </label>
            <label>
                <fmt:message key="sign_up_last_name" bundle="${message}"/>
                <input class="input" type="text"
                       placeholder="nom" name="nom"
                       value="${login.nom}">

            </label>

            <label>
                <fmt:message key="sign_up_first_name" bundle="${message}"/>
                <input class="input" type="text"
                       placeholder="prenom" name="prenom"
                       value="${login.prenom}">
            </label>
            <label>
                <fmt:message key="sign_up_email" bundle="${message}"/>
                <input class="input" type="email"
                       placeholder="email" name="email"
                       value="${login.email}">
            </label>
            <label>
                <fmt:message key="sign_up_phone" bundle="${message}"/>
                <input class="input" type="tel"
                       placeholder="telephone" name="telephone"
                       value="${login.telephone}">
            </label>

        </div>
        <div class="input-container">
            <label>
                <fmt:message key="sign_up_adress" bundle="${message}"/>
                <input class="input" type="text" placeholder="adresse" name="rue" value="${login.rue}">
            </label>
            <label>
                <fmt:message key="sign_up_postal_code" bundle="${message}"/>
                <input class="input" type="text" placeholder="code postale" name="codePostal"
                       value="${login.codePostal}">
            </label>
            <label>
                <fmt:message key="sign_up_town" bundle="${message}"/>
                <input class="input" type="text" placeholder="ville" name="ville" value="${login.ville}">
            </label>
            <label>
                <fmt:message key="sign_up_password" bundle="${message}"/>
                <input class="input" type="password" placeholder="mot de passe" name="password">
            </label>
            <label>
                <fmt:message key="sign_up_confirmation" bundle="${message}"/>
                <input class="input" type="password" placeholder="confirm" name="confirmPassword">
            </label>

        </div>
    </div>

<c:if test="${empty login}">
    <button class="button" type="submit" name="addNewUtilisateur"><fmt:message key="sign_up_create"
                                                                              bundle="${message}"/></button>
    <a href="${pageContext.request.contextPath}/encheres/" class="button"> <fmt:message key="sign-up-cancel"
                                                                                        bundle="${message}"/>
    </a>
</c:if>
    <c:if test="${!empty login}">
    <button class="button" type="submit" name="editUtilisateur" value="${login.noUtilisateur}"><fmt:message key="sign_up_modif"
                                                                              bundle="${message}"/></button>
    <a href="${pageContext.request.contextPath}/encheres/" class="button"> <fmt:message key="sign-up-cancel"
                                                                                        bundle="${message}"/>
    </c:if>


</form>
</body>
</html>
