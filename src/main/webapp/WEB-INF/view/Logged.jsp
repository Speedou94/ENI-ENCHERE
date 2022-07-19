<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 18/07/2022
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../../style/style.css">
    <title>Title</title>
</head>
<body>
<c:if test="${!empty login}">
    <%@include file="./../components/public/navbar-private.jspf"%>
</c:if>
<p>
    nom : ${login.nom} -- prenom : ${login.prenom}
</p>
</body>
</html>
