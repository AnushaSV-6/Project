<%@ page import="java.util.List" %>
<%@ page import="com.nits.model.Course" %>
<%@ page import="com.nits.util.DBUtil" %>
<%@ page import="com.nits.service.CourseService" %>


<%
    // Verify admin session
    String adminEmail = (String) session.getAttribute("adminEmail");
    if (adminEmail == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Fetch course data
   CourseService courseService = new CourseService();
    List<Course> courses = courseService.getAllCourses();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Courses</title>
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
    <h1>Manage Courses</h1>
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
                            <form action="edit_course.jsp" method="get">
                                <input type="hidden" name="code" value="<%= course.getCode() %>">
                                <button type="submit">Edit</button>
                            </form>
                            <form action="delete_course.jsp" method="post">
                                <input type="hidden" name="code" value="<%= course.getCode() %>">
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <a href="add_course.jsp">Add New Course</a>
    <% } else { %>
        <p>No courses found or error retrieving data.</p>
    <% } %>
</body>
</html>
