<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 27/07/2022
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="message_jsp" var="message"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/confirm?action=newpassword">
<div class="modal is-active">
  <div class="modal-background"></div>
  <div class="modal-card">
    <header class="modal-card-head">
      <p class="modal-card-title"> <fmt:message key="modal_change_password_title" bundle="${message}"/></p>
    </header>
    <section class="modal-card-body">
      <label for="password"> <fmt:message key="sign_up_password" bundle="${message}"/>
     <input class="input" type="password" name="password" id="password"></label>
      <label>
        <fmt:message key="sign_up_confirmation" bundle="${message}"/>
        <input class="input" type="password" placeholder="confirm" name="confirmPassword">
      </label>
      <label>
        <fmt:message key="modal_change_password_new_password" bundle="${message}"/>
        <input class="input" type="password" placeholder="confirm" name="newPassword">
          <input type="text" name="idUtilisateur" value="${login.noUtilisateur}" hidden>
      </label>

    </section>
    <footer class="modal-card-foot">
        <button class="button is-success" type="submit" name="valider"> <fmt:message key="sign_up_create" bundle="${message}"/></button>


        <a href="${pageContext.request.contextPath}/encheres/" class="button">
            <fmt:message key="sign-up-cancel" bundle="${message}"/></a>
    </footer>
  </div>
</div>
</form>
</body>
</html>
