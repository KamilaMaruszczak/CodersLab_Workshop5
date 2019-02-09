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
    <th scope = "row" colspan="8">

        All Propositions:

    </th>

    <tr>
        <td> Book Proposition</td>

        <td> Description</td>

        <td> EDIT</td>
        <td>DELETE</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="item">
        <tr>
            <td> ${item.getTitle()}</td>

            <td> ${item.getDescription()}</td>

            <td><a href = "<c:url value = "/proposition/edit/${item.getId()}"/>">EDIT</a></td>
            <td> <a href="<c:url value = "/proposition/delete/${item.getId()}"/>" class="myButton">DELETE</a></td>
        </tr>
    </c:forEach>
    <tr>
    </tbody>

</table>
</body>
</html>
