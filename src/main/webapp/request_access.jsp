<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.useraccess.utils.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Access Request</title>
    <link rel="stylesheet" type="text/css" href="css/requestaccess.css">
    <style>
        .error {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Access Request Form</h2>
        
        <form action="requestservlet" method="post">
            <div class="form-group">
                <label for="software">Software Name:</label>
                <select id="software" name="software" required>
                    <option value="">Select Software</option>
                    <%
                        try (Connection conn = DBConnection.getConnection();
                             Statement stmt = conn.createStatement();
                             ResultSet rs = stmt.executeQuery("SELECT id, name FROM software")) {
                             
                            while (rs.next()) {
                                int id = rs.getInt("id");
                                String name = rs.getString("name");
                    %>
                        <option value="<%= id %>"><%= name %></option>
                    <%
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                    %>
                        <option value="">Error loading software</option>
                    <%
                        }
                    %>
                </select>
            </div>
            <div class="form-group">
                <label for="accessType">Access Type:</label>
                <select id="accessType" name="accessType" required>
                    <option value="">Select Access Type</option>
                    <option value="Read">Read</option>
                    <option value="Write">Write</option>
                    <option value="Admin">Admin</option>
                </select>
            </div>
            <div class="form-group">
                <label for="reason">Reason for Request:</label>
                <textarea id="reason" name="reason" required></textarea>
            </div>
            <button type="submit" class="btn">Submit Request</button>
        </form>
    </div>
    
    <% String statusMessage = (String) request.getAttribute("statusMessage"); %>
<% if (statusMessage != null) { %>
    <div class="status-message"><%= statusMessage %></div>
<% } %>
    
</body>
</html>
