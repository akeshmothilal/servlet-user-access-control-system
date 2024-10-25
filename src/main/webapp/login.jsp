<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <style>
        .error {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Login</h2>

        <%
            String error = request.getParameter("error");
            if (error != null) {
                String errorMessage = "";
                switch (error) {
                    case "invalidCredentials":
                        errorMessage = "Username or password is incorrect.";
                        break;
                    case "databaseError":
                        errorMessage = "A database error occurred. Please try again later.";
                        break;
                    case "invalidRole":
                        errorMessage = "Your role is not valid.";
                        break;
                }
        %>
                <div class="error">
                   <%= errorMessage %>
                </div>
        <%
            }
        %>

        <form action="login" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">Login</button>
        </form>
    </div>
</body>
</html>
