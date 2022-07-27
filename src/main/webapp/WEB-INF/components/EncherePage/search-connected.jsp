<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 22/07/2022
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="message_jsp" var="message"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/details-encheres/filtre/">
    <div class="is-flex">
            <label for="search"> <fmt:message key="search_form_filtres" bundle="${message}"/></label>
            <input id="search" class="search" name="search" type="text" placeholder="<fmt:message key="search_form_search" bundle="${message}"/>">
    </div>
        <br>
    <label for="categories-select"><fmt:message key="search_form_categorie" bundle="${message}"/></label>
    <div class="flex-btn-search">
        <button class="button is-primary is-light" type="submit"><fmt:message key="search_form_button" bundle="${message}"/></button>
    </div>
        <select class ="categories" name="Categories" id="Categories-select">
            <option value="0"><fmt:message key="search_form_select_option" bundle="${message}"/></option>
            <c:forEach items="${listDesCategories}" var="categorie">
                <option value="${categorie.noCategorie}">${categorie.libelle}</option>
            </c:forEach>
        </select>
    <label for="achat">Achat</label>
  <input type="radio" name="select-option" id="achat">
    <div>
        <label for="ouverte">Enchere ouverte</label>
        <input type="checkbox" name="ouverte" value="ouverte" id="ouverte" >
        <label for="mes-encheres">mes-encheres</label>
        <input type="checkbox" name="mes-encheres" value="mes-encheres" id="mes-encheres" >
        <label for="remporte">Enchere remporte</label>
        <input type="checkbox" name="remporte" value="remporte" id="remporte" >
    </div>
  <label for="vente">Mes Ventes</label>
  <input type="radio" name="select-option" id="vente">
    <div>
        <label for="en-cours">Mes encheres en-cours</label>
        <input type="checkbox" name="en-cours" value="en-cours" id="en-cours" >
        <label for="non-debuter">encheres non-debuter</label>
        <input type="checkbox" name="non-debuter" value="non-debuter" id="non-debuter" >
        <label for="terminer">Enchere terminer</label>
        <input type="checkbox" name="terminer" value="terminer" id="terminer" >
    </div>
</form>
</body>
</html>
