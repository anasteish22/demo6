<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<%--${all_users}--%>
<br/>
<table border="2">
    <tr>
        <td>id</td>
        <td>login</td>
        <td>first name</td>
        <td>last name</td>
        <td>phone</td>
    </tr>
    <c:forEach var="user" items="${all_users}">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getLogin()}</td>
            <td>${user.getFirstName()}</td>
            <td>${user.getLastName()}</td>
            <td>${user.getPhoneNumber()}</td>
<%--            <td><c:out value="${user.id}" /> </td>--%>
<%--            <td><c:out value="${user.login}" /> </td>--%>
<%--            <td><c:out value="${user.first}" /> </td>--%>
<%--            <td><c:out value="${user.last}" /> </td>--%>
<%--            <td><c:out value="${user.phone}" /> </td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
