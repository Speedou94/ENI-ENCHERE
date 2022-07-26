<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 26/07/2022
  Time: 09:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${param.lang}" scope="session"/>
<fmt:setBundle basename="message_jsp" var="message"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/confirm?action=${confirmModal}" method="post" class="form">


<div class="modal is-active">
    <div class="modal-background"></div>
    <div class="modal-card">
        <header class="modal-card-head">
            <p class="modal-card-title">Veuillez confirmer ...</p>

        </header>
        <section class="modal-card-body">
           <h2>Merci de confirmer par mdp</h2>
            <label for="password">Mot de passe</label>
            <input type="password" name="password" id="password">
            <input name="idUtilisateur" value="${login.noUtilisateur}" hidden >
            <c:if test="${!empty user}">
               <c:set var="user" value="${user}" scope="request"/>
            </c:if>
        </section>
        <footer class="modal-card-foot">
            <button class="button is-success" type="submit" name="valider">Valider</button>
           <a href="${pageContext.request.contextPath}/encheres/" class="button">Annuler</a>
        </footer>
    </div>
</div>
</form>
</body>
</html>
