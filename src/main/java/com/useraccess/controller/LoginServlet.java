package com.useraccess.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.useraccess.utils.DBConnection;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String query = "SELECT * FROM users WHERE username=? AND password=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                int employeeId = rs.getInt("id");

                HttpSession session = request.getSession();
                session.setAttribute("employeeId", employeeId);
                session.setAttribute("role", role);
                
                switch (role) {
                    case "Employee":
                        response.sendRedirect("request_access.jsp");
                        break;
                    case "Manager":
            			response.sendRedirect("approvalservlet"); // Redirect to GET method to refresh
                        break;
                    case "Admin":
                        response.sendRedirect("create_software.jsp");
                        break;
                    default:
                        redirectWithError(response, "invalidRole");
                }
            } else {
                redirectWithError(response, "invalidCredentials");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            redirectWithError(response, "databaseError");
        }
    }

    private void redirectWithError(HttpServletResponse response, String errorType) throws IOException {
        response.sendRedirect("login.jsp?error=" + errorType);
    }
}
