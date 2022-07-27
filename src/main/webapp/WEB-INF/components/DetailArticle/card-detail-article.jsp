<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 21/07/2022
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="message_jsp" var="message"/>
<html>
<head>
    <title> <fmt:message key="detail_article_titre" bundle="${message}"/></title>
</head>
<body>
<c:if test="${!empty detailArticle}">
  <div class="card">
    <div class="card-image">
      <figure class="image is-128x128">
        <img src="" alt="">
      </figure>
    </div>
    <div class="card-content">
      <div class="media">
        <c:if test="${!empty login}">
          <a href="${pageContext.request.contextPath}/articles?id=${article.noArticle}">${article.nomArticle}</a>
        </c:if>
        <c:if test="${empty login}">
          <div class="media-content">
            <p>${article.nomArticle}</p>
          </div>
        </c:if>
      </div>
    </div>
  </div>
  <div class="content">
   <div>
     <p>
       <fmt:message key="card_detail_article_nom_article" bundle="${message}"/>${detailArticle.nomArticle}
       <br>
       <fmt:message key="card_detail_article_description" bundle="${message}"/>${detailArticle.description}
       <br>
       <fmt:message key="card_detail_article_date_debut" bundle="${message}"/>${detailArticle.dateDebutEncheres}
       <br>
       <fmt:message key="card_detail_article_date_fin" bundle="${message}"/>${detailArticle.dateFinEncheres}
       <br>
       <fmt:message key="card_detail_article_prix_in" bundle="${message}"/>${detailArticle.prixInitial}
       <br>
       <fmt:message key="card_detail_article_montant_enchere" bundle="${message}"/>${meuilleurOffre}
       <br>
       <fmt:message key="card_detail_article_nom_vendeur" bundle="${message}"/>${detailArticle.utilisateur.pseudo}
       <br>
     </p>
   </div>
    <div>
      <c:if test="${!empty login}">
        <form method="post" action="${pageContext.request.contextPath}/details-encheres/">
          <input value="${detailArticle.noArticle}" name="noArticle" hidden>
          <input value="${detailArticle.prixInitial}" name="prixInitial" hidden>
          <input value="${login.noUtilisateur}" name="noUtilisateur" hidden>
          <label for="montant" >Montant</label>
          <input type="number" name="montant" placeholder="montant de votre enchere" id="montant" required>
          <button name="encherir" type="submit" value="encherir">Encherir</button>
        </form>

      </c:if>

    </div>
  </div>
</c:if>
</body>
</html>
