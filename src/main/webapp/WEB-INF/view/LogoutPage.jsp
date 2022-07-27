<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 21/07/2022
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}" scope="session"/>
<fmt:setBundle basename="message_jsp" var="message"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="../../style/style.css">
    <title>Logout</title>
</head>
<body onload="returnAccueil()">


<div class="notification is-link is-light mx-auto max-width50 mt-6">
   <p class="">
       <strong class="is-size-4 has-text-centered">Deconnection de l'application en cours merci de patienter</strong>
   </p>
    <progress class="progress is-large is-info mt-6" max="100"></progress>
</div>


</body>
<script src="../../script/logout.js"></script>
</html>
