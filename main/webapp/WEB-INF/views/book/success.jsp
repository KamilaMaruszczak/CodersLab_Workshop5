<%--
  Created by IntelliJ IDEA.
  User: kamila
  Date: 08.01.19
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book aded</title>
</head>
<body>

    <table border="black" bgcolor="#ffc0cb">
        <th scope = "row" colspan="2">

            You just added a book:

        </th>
        <tr>
            <td> Book</td>
            <td> ${book.title} </td>
        </tr>
        <tr>
            <td> Publisher</td>
            <td> ${book.publisher} </td>
        </tr>
        <tr>


    </table>
</body>
</html>
