<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 22/07/2022
  Time: 07:54
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
<form class="form max-width50 mx-auto" action="${pageContext.request.contextPath}/vente?id=${login.noUtilisateur}" method="post">
  <label for="nomArticle">nom article</label>
    <input class="input is-primary" type="text" placeholder="Primary input" id="nomArticle" name="nomArticle">
    <label for="description">Descrition</label>
    <textarea class="textarea is-primary" placeholder="Primary textarea" id="description" name="description"></textarea>
    <div class="select">
        <select name="categorie">
            <option><fmt:message key="search_form_select_option" bundle="${message}"/></option>
            <c:forEach items="${listDesCategories}" var="categorie">
                <option value="${categorie.noCategorie}">${categorie.libelle}</option>
            </c:forEach>
        </select>
    </div>
    <div class="file has-name">
        <label class="file-label">
            <input class="file-input" type="file" name="resume">
            <span class="file-cta">
      <span class="file-icon">
        <i class="fas fa-upload"></i>
      </span>
      <span class="file-label">
        Choisir un fichier
      </span>
    </span>
            <span class="file-name">
      son nom ici en variable
    </span>
        </label>
    </div>
    <label for="prix">Prix</label>
    <input class="input is-primary" type="number" placeholder="Primary input" id="prix" name="prix">
    <label for="datedebut">Date de debut</label>
    <input type="date" name="datedebut" id="datedebut"  placeholder="date de debut" class="input is-primary" >
    <label for="datefin">Date de fin</label>
    <input type="date" name="datefin" id="datefin" placeholder="date de fin" class="input is-primary" >
    <fieldset>
        <label for="rue">Rue</label>
        <input class="input is-primary" type="text" name="rue" placeholder="Primary input" id="rue" value="${login.rue}">
        <label for="codepostal">Code postal</label>
        <input class="input is-primary" type="text" name="codepostal" placeholder="Primary input" id="codepostal" value="${login.codePostal}">
        <label for="ville">Ville</label>
        <input class="input is-primary" type="text" name="ville" placeholder="Primary input" id="ville" value="${login.ville}">
    </fieldset>

<button class="button is-primary" name="valider" type="submit">Valider</button>
    <button class="button is-info" name="annuler" type="submit">Annuler</button>


</form>
</body>
</html>
