package com.useraccess.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.useraccess.model.Requests;
import com.useraccess.utils.DBConnection;

public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String softwareId = request.getParameter("software");
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");
        
        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("employeeId"); 
        
        if (employeeId == null) {
            response.sendRedirect("login.jsp"); 
            return;
        }

        Requests accessRequest = new Requests(employeeId, Integer.parseInt(softwareId), employeeId, accessType, reason, reason);

        String query = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, 'Pending')";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setInt(1, accessRequest.getUserId());
            pstmt.setInt(2, accessRequest.getSoftwareId());
            pstmt.setString(3, accessRequest.getAccessType());
            pstmt.setString(4, accessRequest.getReason());
            pstmt.executeUpdate();

            request.setAttribute("statusMessage", "Access request submitted successfully. Status: Pending.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("statusMessage", "Failed to submit request due to a database error.");
        }

        request.getRequestDispatcher("request_access.jsp").forward(request, response);
    }
}
