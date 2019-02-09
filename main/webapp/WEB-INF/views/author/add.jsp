<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kamila
  Date: 08.01.19
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
Add new author:
<form:form modelAttribute="author" method="post">
    First Name<form:input path="firstName"/><br>
    <form:errors path="firstName" cssClass="error" element="div" />
    Last Name<form:input path="lastName"/><br>
    <form:errors path="lastName" cssClass="error" element="div" />
    PESEL<form:input path="pesel"/><br>
    <form:errors path="pesel" cssClass="error" element="div" />
    E-mail<form:input path="email"/><br>
    <form:errors path="email" cssClass="error" element="div" />
    Year of birth<form:input path="yearOfBirth"/><br>
    <form:errors path="yearOfBirth" cssClass="error" element="div" />
    <input type="submit" value="Wyślij"/>
</form:form>

</body>
</html>
