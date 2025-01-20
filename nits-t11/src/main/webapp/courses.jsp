<%@ page import="java.util.List" %>
<%@ page import="com.nits.model.Course" %>
<%@ page import="com.nits.model.User" %>
<%@ page import="com.nits.service.CourseService" %>

<%
User loggedInUser = (User) session.getAttribute("loggedInUser");
if (loggedInUser == null) {
    response.sendRedirect("login.jsp");
    return;
}

// Get the user's email
String userEmail = loggedInUser.getEmail();
CourseService courseService = new CourseService();
List<Course> courses = courseService.getAllCourses();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Available Courses to Enroll</title>
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
    <h1>Available Courses</h1>
    <% if (courses != null && !courses.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>Course Code</th>
                    <th>Course Name</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% for (Course course : courses) { %>
                    <tr>
                        <td><%= course.getCode() %></td>
                        <td><%= course.getName() %></td>
                        <td><%= course.getDescription() %></td>
                        <td>
<form action="enroll" method="post">
    <input type="hidden" name="code" value="<%= course.getCode() %>">
    <input type="hidden" name="coursename" value="<%= course.getName() %>">
    <button type="submit">Enroll</button>
</form>

                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>No courses available at the moment.</p>
    <% } %>
    <% 

%>
    
</body>
</html>
