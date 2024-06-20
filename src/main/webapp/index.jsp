<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Start page</title>
    <h1>Log in</h1>
</head>
<body>
<br/>
<form action="login.do" method="get">
    <input type="hidden" name="command" value="login"/>
    Login : <input type="text" name="login"/>
    <br/>
    Password: <input type="password" name="password"/>
    <br/>
    <br/>
    <input type="submit" name="submit" value="Log in"/>
    <br/>
    ${login_msg}
</form>
Don't have an account?
<a href="sign_up.jsp">Sign up</a>
<form action="view_all_users.do" method="get">
    <input type="submit" name="submit" value="All users">
</form>
</body>
</html>