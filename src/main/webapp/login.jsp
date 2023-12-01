<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/static/style.css"%></style>
<html>
<body>
<header class="center-items">
    <h1 class="title">Авторизация</h1>
</header>
<div class="flex-column center-items">
    <form class="flex-column center-items" action="<c:url value="/login"/>" method="post">
        <div>
            <p>Логин</p>
            <input name="login" type="text" class="input" required>
        </div>
        <div style="margin-top: 15px">
            <p>Пароль</p>
            <input name="password" type="password" class="input" required>
        </div>
        <div style="display: flex; justify-content: center; margin-top: 10%">
            <button class="button" type="submit">Войти</button>
        </div>
    </form>
</div>
</body>
</html>
