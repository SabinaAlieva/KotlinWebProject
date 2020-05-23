<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Log in with your account</title>
  <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div>
  <h1>Заказы</h1>
  <table>
    <thead>
    <th>Клиент</th>
    <th>Товар</th>
    </thead>
    <c:forEach items="${allItems}" var="order">
      <tr>
        <td>${order.user.username}</td>
        <td>${order.item.category}</td>
        <td>
          <form action="${pageContext.request.contextPath}/orders" method="post">
            <input type="hidden" name="userId" value="${user.id}"/>
            <input type="hidden" name="orderId" value="${order.id}"/>
            <button type="submit">Удалить</button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>
  <h4><a href="/">На главную</a></h4>
</div>
</body>
</html>