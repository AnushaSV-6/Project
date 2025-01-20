<%@ page import="com.nits.util.DBUtil" %>
<%@ page import="com.nits.model.Course" %>
<%@ page import="com.nits.dao.CourseDAO" %>
<%@ page import="com.nits.service.CourseService" %>


<%@ page import="com.nits.service.CourseService" %>
<%@ page import="java.io.PrintWriter" %>

<%
    String code = request.getParameter("code");
    String name = request.getParameter("name");
    String description = request.getParameter("description");

    boolean isAdded = false;
    String message = "";

    try {
        CourseService courseService = new CourseService();
        isAdded = courseService.addCourse(code, name, description);

        if (isAdded) {
            message = "Course added successfully!";
        } else {
            message = "Failed to add course. Please try again.";
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "An error occurred while adding the course.";
    }
%>


<!DOCTYPE html>
<html>
<head>
    <title>Add Course</title>
</head>
<body>
    <h1>Add New Course</h1>
    <form action="add_course.jsp" method="post">
        <label for="code">Course Code:</label>
        <input type="text" id="code" name="code" required><br>

        <label for="name">Course Name:</label>
        <input type="text" id="name" name="name" required><br>

        <label for="description">Description:</label>
        <textarea id="description" name="description" required></textarea><br>

        <button type="submit">Add Course</button>
    </form>
    <br>
    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>
