<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Software</title>
    <link rel="stylesheet" type="text/css" href="css/software.css">
</head>
<body>
    <div class="container">
        <h2>Create New Software</h2>

        <form action="softwareservlet" method="post">
            <div class="form-group">
                <label for="name">Software Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" required></textarea>
            </div>
            <div class="form-group">
                <label>Access Levels:</label><br>
                <input type="checkbox" name="accessLevels" value="Read"> Read<br>
                <input type="checkbox" name="accessLevels" value="Write"> Write<br>
                <input type="checkbox" name="accessLevels" value="Admin"> Admin<br>
            </div>
            <button type="submit" class="btn">Create Software</button>
        </form>
    </div>
</body>
</html>
