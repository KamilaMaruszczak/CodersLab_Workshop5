<%--
  Created by IntelliJ IDEA.
  User: kamila
  Date: 09.01.19
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Validation</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div>
    <span class="big">Error during ${type} ${action}:</span>
<c:forEach items="${validation}" var="item">
    <p><span class="red">${item.getPropertyPath()}</span> - ${item.getMessage()}</p>
</c:forEach>
</div>
<div>
    <span class="big">Try to ${action} ${type} again:</span>

    <c:choose>
        <c:when test="${action.equals(add)}">
            <a href = "<c:url value = "/${type}/${action}"/>" class="myButton">${action.toUpperCase()} ${type.toUpperCase()}</a>
        </c:when>
        <c:otherwise>
            <a href = "<c:url value = "/${type}/${action}/${id}"/>" class="myButton">${action.toUpperCase()} ${type.toUpperCase()}</a>
        </c:otherwise>
    </c:choose>


</div>
</body>
</html>
