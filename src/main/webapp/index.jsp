<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/static/style.css"%></style>

<html>
<body>
<div class="flex-column center-items">
    <h1>Привет, ${user.name}</h1>
    <h3 style="margin-top: 1%">Сейчас ${now}</h3>
</div>
</body>
</html>