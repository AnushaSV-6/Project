<%@ page import="java.util.List" %>
<%@ page import="com.nits.model.User" %>
<%@ page import="com.nits.util.DBUtil" %>
<%@ page import="com.nits.service.UserService" %>

<%
    // Verify admin session
    String adminEmail = (String) session.getAttribute("adminEmail");
    if (adminEmail == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Fetch data from database
UserService userService = new UserService(); // Create an instance
List<User> users = userService.getAllUsers(); // Call the non-static method
    
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        .actions form {
            display: inline-block;
        }
    </style>
</head>
<body>
    <div class="container">
      

        <!-- User Table -->
        <h2>Registered Users</h2>
        <% if (users != null && !users.isEmpty()) { %>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Gender</th>
                        <th>Education</th>
                        <th>Languages</th>
                        <th>About</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (User user : users) { %>
                        <tr>
                            <td><%= user.getName() %></td>
                            <td><%= user.getEmail() %></td>
                            <td><%= user.getPhone() %></td>
                            <td><%= user.getGender() %></td>
                            <td><%= user.getEducation() %></td>
                            <td><%= user.getLanguages() %></td>
                            <td><%= user.getAbout() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No users found or error retrieving data.</p>
        <% } %>
