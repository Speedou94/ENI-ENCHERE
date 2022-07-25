<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 21/07/2022
  Time: 21:49
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
<c:forEach items="${articlesDisponible}" var="article">

    <div class="card">
        <div class="card-image">
            <figure class="image is-128x128">
                <img src="" alt="Placeholder image">
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

            <div class="content">
                <p><fmt:message key="card_encheres_content" bundle="${message}"/>${article.prixInitial} <fmt:message key="card_encheres_price" bundle="${message}"/><br>
                    <fmt:message key="card_encheres_fin_enchere" bundle="${message}"/>${article.dateFinEncheres}<br>
                    <fmt:message key="card_encheres_vendeur" bundle="${message}"/>${article.utilisateur.pseudo}</p>
            </div>
        </div>
    </div>

</c:forEach>
</body>
</html>
