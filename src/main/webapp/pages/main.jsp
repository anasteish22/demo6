<%--
  Created by IntelliJ IDEA.
  User: afran
  Date: 09/06/2024
  Time: 12:10 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
Hello ${user}
<br/>
<form action="add_user.do" method="get">
    Login : <input type="text" name="login"/>
    <br/>
    Password: <input type="password" name="password"/>
    <br/>
    <input type="submit" name="submit" value="Push"/>
    <br/>
    ${login_msg}
</form>
</body>
</html>
