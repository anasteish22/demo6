<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<br/>
<form action="login.do" method="get">
    <input type="hidden" name="command" value="login"/>
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