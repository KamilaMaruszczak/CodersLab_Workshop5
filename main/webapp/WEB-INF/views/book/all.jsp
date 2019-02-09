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
    <th scope = "row" colspan="9">

        All books:

    </th>

    <tr>
        <td> Book</td>
        <td>Author / Authors</td>
        <td> Rating</td>
        <td> Description</td>
        <td> Publisher </td>
        <td> Pages </td>
        <td> Category </td>
        <td> EDIT</td>
        <td>DELETE</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="item">
    <tr>
        <td> ${item.getTitle()}</td>
        <td>
            <c:forEach items="${item.authors}" var="author">
                ${author.fullName}<br>
            </c:forEach>
        </td>
        <td> ${item.getRating()}</td>
        <td> ${item.getDescription()}</td>
        <td> ${item.publisher.name} </td>
        <td> ${item.pages} </td>
        <td> ${item.category.name} </td>
        <td><a href = "<c:url value = "/book/edit/${item.getId()}"/>">EDIT</a></td>
        <td> <a href="<c:url value = "/book/delete/${item.getId()}"/>" class="myButton">DELETE</a></td>
    </tr>
    </c:forEach>
    <tr>
    </tbody>

</table>
</body>
</html>
