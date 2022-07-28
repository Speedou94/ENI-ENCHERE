<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 22/07/2022
  Time: 07:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="message_jsp" var="message"/>
<c:set var="langue_choisie" value="${param.lang}"/>no_categorie

<html>
<head>
    <title><fmt:message key="formulaire_vente_title" bundle="${message}"/></title>
</head>
<body>
<header>
    <div class="sell-form">
        <h1>
            <fmt:message key="formulaire_vente_title" bundle="${message}"/>
        </h1>
    </div>
</header>
<form class="form max-width50 mx-auto" action="${pageContext.request.contextPath}/vente?id=${login.noUtilisateur}"
      method="post">
    <label for="nomArticle" class="nameArticle"><fmt:message key="formulaire_vente_name_article" bundle="${message}"/>
    </label>
    <input class="input is-primary" type="text" placeholder="
    <fmt:message key="formulaire_vente_name_article" bundle="${message}"/>" id="nomArticle" name="nomArticle">
    <label for="description" class="description"> <fmt:message key="formulaire_vente_description" bundle="${message}"/>
    </label>
    <textarea class="textarea is-primary" placeholder="
    <fmt:message key="formulaire_vente_description" bundle="${message}"/>" id="description"
              name="description"></textarea>

        <select name="categorie" class="categoriesSell">
            <option><fmt:message key="search_form_select_option" bundle="${message}"/></option>
            <c:forEach items="${listDesCategories}" var="categorie">
                <option value="${categorie.noCategorie}">${categorie.libelle}</option>
            </c:forEach>
        </select>

    <div class="file has-name">
        <label class="file-label">
            <input class="file-input" type="file" name="resume">
            <span class="file-cta">
      <span class="file-icon">
        <i class="fas fa-upload"></i>
      </span>
      <span class="file-label"><fmt:message key="formulaire_vente_fichier" bundle="${message}"/>
      </span>
    </span>
            <span class="file-name">
      Fonctionabilite non presente
    </span>
        </label>
    </div>
    <label for="prix" class="prix"> <fmt:message key="formulaire_vente_price" bundle="${message}"/>
    </label>
    <input class="input is-primary" type="number" placeholder="Primary input" id="prix" name="prix">
    <label for="datedebut" class="date-debut"><fmt:message key="formulaire_vente_start_date"
                                                           bundle="${message}"/></label>
    <input type="date" name="datedebut" id="datedebut" placeholder="date de debut" class="input is-primary">
    <label for="datefin" class="date-fin"><fmt:message key="formulaire_vente_end_date" bundle="${message}"/></label>
    <input type="date" name="datefin" id="datefin" placeholder="date de fin" class="input is-primary">
    <fieldset>
        <label for="rue" class="rue-enchere"><fmt:message key="formulaire_vente_street" bundle="${message}"/></label>
        <input class="input is-primary" type="text" name="rue" placeholder="Primary input" id="rue"
               value="${login.rue}">
        <label for="codepostal" class="ZIP-enchere"><fmt:message key="formulaire_vente_zip"
                                                                 bundle="${message}"/></label>
        <input class="input is-primary" type="text" name="codepostal" placeholder="Primary input" id="codepostal"
               value="${login.codePostal}">
        <label for="ville" class="street-enchere"><fmt:message key="formulaire_vente_city" bundle="${message}"/></label>
        <input class="input is-primary" type="text" name="ville" placeholder="Primary input" id="ville"
               value="${login.ville}">
    </fieldset>
    <div class="enchere-buttons">
        <button class="button is-primary is-medium" name="valider" type="submit"><fmt:message
                key="formulaire_vente_confirm"
                bundle="${message}"/></button>
        <a href="${pageContext.request.contextPath}/encheres/" class="button is-danger is-medium"> <fmt:message key="sign-up-cancel"
                                                                                            bundle="${message}"/>
        </a>
    </div>

</form>
</body>
</html>
