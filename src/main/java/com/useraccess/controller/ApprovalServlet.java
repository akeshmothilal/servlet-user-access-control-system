package com.useraccess.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.useraccess.model.AccessResopnse;
import com.useraccess.utils.DBConnection;

public class ApprovalServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<AccessResopnse> accessResponse = getPendingRequests();
			request.setAttribute("accessResponse", accessResponse);
			request.getRequestDispatcher("pending_requests.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
		}
	}

	private List<AccessResopnse> getPendingRequests() throws SQLException {
		List<AccessResopnse> accessResponse = new ArrayList<>();

		String query = "SELECT r.id, u.username AS employee_name, s.name AS software_name, "
	             + "r.access_type, r.reason FROM requests r "
	             + "JOIN users u ON r.user_id = u.id "
	             + "JOIN software s ON r.software_id = s.id "
	             + "WHERE r.status = 'Pending'";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				int requestId = rs.getInt("id");
				String employeeName = rs.getString("employee_name");
				String softwareName = rs.getString("software_name");
				String accessType = rs.getString("access_type");
				String reason = rs.getString("reason");

				accessResponse.add(new AccessResopnse(requestId, employeeName, softwareName, accessType, reason));
			}
		}
		System.out.println(accessResponse);
		return accessResponse;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		System.out.println("requestId is :"+requestId);


		try {
			if ("approve".equals(action)) {
				updateRequestStatus(requestId, "Approved");
			} else if ("reject".equals(action)) {
				updateRequestStatus(requestId, "Rejected");
			}
			response.sendRedirect("approvalservlet"); // Redirect to GET method to refresh
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
		}
	}

	private void updateRequestStatus(int requestId, String status) throws SQLException {
		String query = "UPDATE requests SET status = ? WHERE id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, status);
			pstmt.setInt(2, requestId);
			pstmt.executeUpdate();
		}
	}
}
