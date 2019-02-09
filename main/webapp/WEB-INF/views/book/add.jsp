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

<form:form modelAttribute="book" method="post" class="form-container">
    Title<form:input path="title" class="form-field"/>
    <form:errors path="title" cssClass="error" element="div" />
    Author<form:select path="authors" multiple="true" class="form-field">
        <form:options items="${authors}" itemValue="id" itemLabel="fullName" path="author.id" />
        </form:select><br>
    <form:errors path="authors" cssClass="error" element="div" />
    Rating<form:input path="rating" class="form-field"/>
    <form:errors path="rating" cssClass="error" element="div" />
    Description<form:input path="description" class="form-field"/>
    <form:errors path="description" cssClass="error" element="div" />
    Publisher<form:select path="publisher" class="form-field" >
        <%--<form:option label="Empty" value="0"></form:option>--%>
        <form:options items="${publishers}" itemValue="id" itemLabel="name" path="publisher.id"/>
        </form:select>
    <form:errors path="publisher" cssClass="error" element="div" />
    Pages<form:input path="pages" class="form-field"/>
    <form:errors path="pages" cssClass="error" element="div" />
    Category<form:select path="category" class="form-field" >
    <%--<form:option value=""></form:option>--%>
    <form:options items="${categories}" itemValue="id" itemLabel="name"/>
</form:select>
    Proposition<form:checkbox path="proposition"/>
    <form:errors path="proposition" cssClass="checkboxError" element="div" />
<div class="submit-container">
    <input type="submit" value="Wyślij" class="submit-button"/>
</div>
</form:form>

</body>
</html>
