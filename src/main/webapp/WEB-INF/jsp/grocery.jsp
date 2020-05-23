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
    <h1>Каталог товаров</h1>
  <table>
    <thead>
    <th>Название</th>
    <th>Описание</th>
    <th>Цена</th>
    </thead>
    <c:forEach items="${allItems}" var="item">
      <tr>
        <td>${item.category}</td>
        <td>${item.description}</td>
        <td>${item.price}</td>
        <c:if test="${pageContext.request.isUserInRole('USER')}">
            <td>
              <form action="${pageContext.request.contextPath}/grocery" method="post">
                <input type="hidden" name="userId" value="${user.id}"/>
                <input type="hidden" name="itemId" value="${item.id}"/>
                <button type="submit">Купить</button>
              </form>
            </td>
           </c:if>
         <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
             <td>
               <form action="${pageContext.request.contextPath}/grocery_delete" method="post">
                 <input type="hidden" name="itemId" value="${item.id}"/>
                 <button type="submit">Удалить</button>
               </form>
             </td>
            </c:if>
      </tr>
    </c:forEach>
  </table>
    <h4><a href="/">На главную</a></h4>
</div>
</body>
</html>