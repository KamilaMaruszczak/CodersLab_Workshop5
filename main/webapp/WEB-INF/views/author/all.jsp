<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<%--
  Created by IntelliJ IDEA.
  User: kamila
  Date: 08.01.19
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/style.css">
    <title>AllBooks</title>
</head>
<body>
<table class="darkTable">
    <thead>
    <th scope = "row" colspan="6">

        All authors:

    </th>

    <tr>
        <td> First Name</td>
        <td> Last Name</td>
        <td> PESEL</td>
        <td> E-mail</td>
        <td> EDIT</td>
        <td>DELETE</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${authors}" var="item">
    <tr>
        <td> ${item.firstName}</td>
        <td> ${item.lastName}</td>
        <td> ${item.pesel}</td>
        <td> ${item.email}</td>
        <td><a href = "<c:url value = "/author/edit/${item.id}"/>">EDIT</a></td>
        <td> <a href="<c:url value = "/author/delete/${item.id}"/>" class="myButton">DELETE</a></td>
    </tr>
    </c:forEach>
    <tr>
    </tbody>

</table>
</body>
</html>
