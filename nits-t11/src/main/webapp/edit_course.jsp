<%@ page import="com.nits.model.Course" %>
<%@ page import="com.nits.service.CourseService" %>

<%
    // Verify admin session
    String adminEmail = (String) session.getAttribute("adminEmail");
    if (adminEmail == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Fetch course details from the request parameter
    String code = request.getParameter("code");
    Course course = null;
    String message = "";

    try {
        if (code == null || code.isEmpty()) {
            message = "Course code is missing!";
        } else {
            CourseService courseService = new CourseService();
            course = courseService.getCourseByCode(code);

            if (course == null) {
                message = "Course not found for code: " + code;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "An error occurred while retrieving the course details.";
    }

    // Handle form submission
    String name = request.getParameter("name");
    String description = request.getParameter("description");

    if (name != null && description != null && course != null) {
        try {
            CourseService courseService = new CourseService();
            boolean isUpdated = courseService.updateCourse(code, name, description);

            if (isUpdated) {
                response.sendRedirect("manage_course.jsp?message=Course updated successfully!");
                return;
            } else {
                message = "Failed to update course. Please try again.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "An error occurred while updating the course.";
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Course</title>
</head>
<body>
    <h1>Edit Course</h1>

    <% if (!message.isEmpty()) { %>
        <h3><%= message %></h3>
        <a href="manage_course.jsp">Back to Manage Courses</a>
    <% } else if (course != null) { %>
        <form action="edit_course.jsp" method="post">
            <input type="hidden" name="code" value="<%= course.getCode() %>">

            <label for="name">Course Name:</label>
            <input type="text" id="name" name="name" value="<%= course.getName() %>" required><br><br>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required><%= course.getDescription() %></textarea><br><br>

            <button type="submit">Update Course</button>
        </form>
        <a href="manage_course.jsp">Cancel</a>
    <% } %>
</body>
</html>
