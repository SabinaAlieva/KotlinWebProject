<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Log in with your account</title>
  <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<sec:authorize access="isAuthenticated()">
  <% response.sendRedirect("/"); %>
</sec:authorize>
<div>
  <form method="POST" action="/login">
    <h1>Вход</h1>
    <input name="username" type="text" placeholder="Логин"
             autofocus="true"/>
    <input name="password" type="password" placeholder="Пароль"/>
    <button type="submit">Войти</button>
    <h4><a href="/registration">Регистрация</a></h4>
    <h4><a href="/">На главную</a></h4>
  </form>
</div>

</body>
</html>