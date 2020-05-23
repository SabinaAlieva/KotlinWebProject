<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Log in with your account</title>
  <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div>
  <h1>Страница управления</h1>
  <table>
    <thead>
    <th>Пользователь</th>
    <th>Роль</th>
    </thead>
    <c:forEach items="${allUsers}" var="user">
      <tr>
        <td>${user.username}</td>
        <td>
          <c:forEach items="${user.roles}" var="role">${role.name}; </c:forEach>
        </td>
        <td>
          <form action="${pageContext.request.contextPath}/admin" method="post">
            <input type="hidden" name="userId" value="${user.id}"/>
            <input type="hidden" name="action" value="delete"/>
            <button type="submit">Удалить</button>
          </form>
          <c:forEach items="${user.roles}" var="role">
                   <c:if test="${role.name == 'ROLE_MANAGER'}">
                       <c:set var = "is_manager" scope = "session" value = "True"/>
                   </c:if>
               </c:forEach>

               <c:choose>
                 <c:when test="${is_manager == 'True'}">
                   <form action="${pageContext.request.contextPath}/admin" method="post">
                     <input type="hidden" name="userId" value="${user.id}"/>
                     <input type="hidden" name="action" value="removeRole"/>
                     <button type="submit">Отключить менеджера</button>
                   </form>
                 </c:when>
                 <c:otherwise>
                   <form action="${pageContext.request.contextPath}/admin" method="post">
                     <input type="hidden" name="userId" value="${user.id}"/>
                     <input type="hidden" name="action" value="addRole"/>
                     <button type="submit">Подключить менеджером</button>
                   </form>
                 </c:otherwise>
               </c:choose>
               <c:set var = "is_manager" scope = "session" value = "False"/>
        </td>
      </tr>
    </c:forEach>
  </table>
      <div>
        <form:form action="${pageContext.request.contextPath}/admin_new_item" method="POST" modelAttribute="groceryForm">
          <h1>Добавить товар</h1>
          <div>
            Название: <form:input type="text" path="category" autofocus="true"></form:input>
          </div>
          <div>
            Описание: <form:input type="text" path="description"></form:input>
          </div>
          <div>
            Цена: <form:input type="text" path="price"></form:input>
          </div>
          <div>
            <button type="submit">Добавить</button>
          </div>
        </form:form>
      </div>
        <div>
          <form action="${pageContext.request.contextPath}/admin_change_item" method="POST">
            <h1>Изменить</h1>
            <div>
              Id: <input type="text" name="id"></input>
            </div>
            <div>
              Название: <input type="text" name="category"></input>
            </div>
            <div>
              Описание: <input type="text" name="description"></input>
            </div>
            <div>
              Цена: <input type="text" name="price"></input>
            </div>
            <div>
              <button type="submit">Изменить</button>
            </div>
          </form>
        </div>
  <h4><a href="/">На главную</a></h4>
</div>
</body>
</html>