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
    <title>Lougout</title>
</head>
<body>
Vous etes deconnecter de ENI enchere
<!-- TODO : Mettre un bouton pour revenir a la page d'accueil en allant faire un get sur la servlet HomeController-->

</body>
</html>