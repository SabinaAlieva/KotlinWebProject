<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Регистрация</title>
  <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div>
  <form:form method="POST" modelAttribute="userForm">
    <h1>Новый аккаунт</h1>
    <div>
      <form:input type="text" path="username" placeholder="Логин"
                  autofocus="true"></form:input>
      <form:errors path="username"></form:errors>
        ${usernameError}
    </div>
    <div>
      <form:input type="password" path="password" placeholder="Пароль"></form:input>
    </div>
    <div>
      <form:input type="password" path="passwordConfirm"
                  placeholder="Повторите пароль"></form:input>
      <form:errors path="password"></form:errors>
        ${passwordError}
    </div>
    <div>
      <button type="submit">Регистрация</button>
    </div>
  </form:form>
  <h4><a href="/">На главную</a></h4>
</div>
</body>
</html>