<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<h1>Sign Up</h1>
<form action="signup-servlet" method="post">
    <label for="newUsername">Username:</label>
    <input type="text" id="newUsername" name="username" required>
    <br>
    <label for="newPassword">Password:</label>
    <input type="password" id="newPassword" name="password" required>
    <br>
    <button type="submit">Sign Up</button>
</form>
<a href="index.jsp">Back to Login</a>
</body>
</html>