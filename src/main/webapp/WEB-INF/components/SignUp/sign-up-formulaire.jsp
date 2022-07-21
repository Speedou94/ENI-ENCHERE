<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 21/07/2022
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message_jsp" var="message"/>
<html>
<head>
    <title>sign-up-formulaire</title>
</head>
<body>
<form method="post" action ="${pageContext.request.contextPath}/utilisateurs/">
    <div class="form-new-user">
        <div class="input-container">
            <label> <fmt:message key="sign_up_form" bundle="${message}"/>
                <input class="input" type="text" placeholder="pseudo" name="pseudo">
            </label>
            <label>
                <fmt:message key="sign_up_last_name" bundle="${message}"/><input class="input" type="text" placeholder="nom" name="nom">
            </label>

            <label>
                <fmt:message key="sign_up_first_name" bundle="${message}"/><input class="input" type="text" placeholder="prenom" name="prenom">
            </label>
            <label>
                <fmt:message key="sign_up_email" bundle="${message}"/><input class="input" type="email" placeholder="email" name="email">
            </label>
            <label>
                <fmt:message key="sign_up_phone" bundle="${message}"/><input class="input" type="tel" placeholder="telephone" name="telephone">
            </label>

        </div>
        <div class="input-container">
            <label><fmt:message key="sign_up_adress" bundle="${message}"/>
                <input class="input" type="text" placeholder="adresse" name="rue"></label>
            <label> <fmt:message key="sign_up_postal_code" bundle="${message}"/>
                <input class="input" type="text" placeholder="code postale" name="codePostal"></label>
            <label> <fmt:message key="sign_up_town" bundle="${message}"/>
                <input class="input" type="text" placeholder="ville" name="ville"></label>
            <label> <fmt:message key="sign_up_password" bundle="${message}"/>
                <input class="input" type="password" placeholder="mot de passe" name="password"></label>
            <label> <fmt:message key="sign_up_confirmation" bundle="${message}"/>
                <input class="input" type="password" placeholder="confirm" name="confirmPassword"></label>

        </div>
    </div>



    <button class="button" type="submit" name="addNewUtulisteur"><fmt:message key="sign_up_create" bundle="${message}"/></button>
    <a href="${pageContext.request.contextPath}/encheres/" class="button"> <fmt:message key="sign-up-cancel" bundle="${message}"/></a>


</form>
</body>
</html>
