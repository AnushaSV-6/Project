<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.nits.model.UserCourse" %>
<%@ page import="com.nits.service.CourseService" %>

<%
    // Verify admin session
    String inEmail = (String) session.getAttribute("inEmail");
    if (inEmail == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Fetch user-course enrollments
    CourseService courseService = new CourseService();
    List<UserCourse> userCourses = courseService.getAllUserCourses();
%>
<!DOCTYPE html>
<html>
<head>
    <title>User-Course Enrollments</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
        }
    </style>
</head>
<body>
    <h1>User-Course Enrollments</h1>
    <% if (userCourses != null && !userCourses.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>User Email</th>
                    <th>Course Code</th>
                    <th>Course Name</th>
                </tr>
            </thead>
            <tbody>
                <% for (UserCourse userCourse : userCourses) { %>
                    <tr>
                        <td><%= userCourse.getEmail() %></td>
                        <td><%= userCourse.getCode() %></td>
                        <td><%= userCourse.getCourseName() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>No enrollments found or error retrieving data.</p>
    <% } %>
</body>
</html>
