
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <h1>Sign up</h1>
</head>
<body>
<form action="sign_up.do" method="get">
    <input type="hidden" name="command" value="sign_up"/>
    First name: <input type="text" name="first">
    <br/>
    Last name: <input type="text" name="last">
    <br/>
    Login: <input type="text" name="login">
    <br/>
    Password: <input type="password" name="password">
    <br/>
    Phone number: <input type="tel" name="phone" pattern="^(\+\d{1,3})\d{9,13}$">
    <br/>
    <br/>
    <input type="submit" name="submit" value="Create an account"/>
    <br/>
    ${sign_up_msg}
</form>
</body>
</html>
