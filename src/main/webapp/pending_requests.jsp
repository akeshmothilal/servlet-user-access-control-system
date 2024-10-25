<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.useraccess.model.AccessResopnse" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pending Access Requests</title>
    <link rel="stylesheet" type="text/css" href=css/pending.css> <!-- Link to your CSS file -->
</head>
<body>
    <div class="container">
        <h1>Pending Access Requests</h1>
        <table>
            <thead>
                <tr>
                    <th>Employee Name</th>
                    <th>Software Name</th>
                    <th>Access Type</th>
                    <th>Reason for Request</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<AccessResopnse> accessResponse = (List<AccessResopnse>) request.getAttribute("accessResponse");
                    if (accessResponse != null) {
                        for (AccessResopnse accessRequest : accessResponse) { 
                %>
                    <tr>
                        <td><%= accessRequest.getEmployeeName() %></td> 
                        <td><%= accessRequest.getSoftwareName() %></td>
                        <td><%= accessRequest.getAccessType() %></td>
                        <td><%= accessRequest.getReason() %></td>
                        <td>
                            <form action="approvalservlet" method="post">
                                <input type="hidden" name="requestId" value="<%= accessRequest.getId() %>">
                                <button type="submit" name="action" value="approve">Approve</button>
                                <button type="submit" name="action" value="reject">Reject</button>
                            </form>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5">No pending requests.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
