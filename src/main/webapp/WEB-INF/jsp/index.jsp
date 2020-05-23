<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
  <title>Главная</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
<div>
  <img src="../css/pizzeria.jpg" alt="Пиццерия">
  <h1>Пиццерия</h1>
  <h3>${pageContext.request.userPrincipal.name}</h3>
  <sec:authorize access="!isAuthenticated()">
    <h4><a href="/login">Вход</a></h4>
    <h4><a href="/registration">Регистрация</a></h4>
  </sec:authorize>
  <sec:authorize access="isAuthenticated()">
    <h4><a href="/logout">Выход</a></h4>
  </sec:authorize>
  <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
     <h4><a href="/admin">Управление</a></h4>
  </c:if>
  <h4><a href="/grocery">Каталог товаров</a></h4>
  <c:if test="${pageContext.request.isUserInRole('USER')}">
    <h4><a href="/orders">Заказы</a></h4>
    </c:if>
</div>
</body>
</html>