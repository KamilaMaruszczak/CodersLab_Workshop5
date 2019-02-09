<%--
  Created by IntelliJ IDEA.
  User: kamila
  Date: 08.01.19
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <link rel="stylesheet" href="/css/style.css">
    <title>Confirm</title>
</head>
<body>
Confirm delating ${type}:
<a href = "/${type}/all" class="myButton">DISMISS</a>
<a href="/${type}/deleteconfirm/${id}" class="myButton">DELETE</a>
</body>
</html>
