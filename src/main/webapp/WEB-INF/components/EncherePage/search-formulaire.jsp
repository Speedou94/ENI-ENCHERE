<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 21/07/2022
  Time: 21:59
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
<form action="${pageContext.request.contextPath}/encheres/">
    <div class="is-flex">
        <label for="search"> <fmt:message key="search_form_filtres" bundle="${message}"/></label>
        <input id="search" class="input" name="search" type="text" placeholder="<fmt:message key="search_form_search" bundle="${message}"/>">
    </div>
    <br>
    <label for="categories-select"><fmt:message key="search_form_categorie" bundle="${message}"/></label>
    <div class="flex-btn-search">
        <button class="button is-primary is-light"><fmt:message key="search_form_button" bundle="${message}"/></button>
    </div>
    <select name="Categories" id="Categories-select">
        <option value="0"><fmt:message key="search_form_select_option" bundle="${message}"/></option>
        <c:forEach items="${listDesCategories}" var="categorie">
            <option value="${categorie.noCategorie}">${categorie.libelle}</option>
        </c:forEach>
    </select>



</form>
</body>
</html>
