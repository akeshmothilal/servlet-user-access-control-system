package com.useraccess.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.useraccess.model.Software;
import com.useraccess.utils.DBConnection;

public class SoftwareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        String[] levels = request.getParameterValues("accessLevels");
        String accessLevels = (levels != null) ? String.join(", ", levels) : "";

        Software software = new Software();
        software.setName(name);
        software.setDescription(description);
        software.setAccessLevels(accessLevels);

        String query = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setString(1, software.getName());
            pstmt.setString(2, software.getDescription());
            pstmt.setString(3, software.getAccessLevels());
            pstmt.executeUpdate();

            response.sendRedirect("success.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("create_software.jsp?error=databaseError");
        }
    }
}
