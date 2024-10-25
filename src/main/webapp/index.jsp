<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
   
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>
    <div class="container">
        <h2>Registeration</h2>
        <form action="signup" method="post">
           <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <input type="hidden" name="role" value="Employee">
            <button type="submit" class="btn">Register</button>
        </form>
    </div>
    <script src="script.js"></script>
</body>
</html>
