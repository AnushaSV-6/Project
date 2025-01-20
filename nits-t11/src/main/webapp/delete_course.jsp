<%@ page import="com.nits.util.DBUtil" %>
<%@ page import="com.nits.service.CourseService" %>

<%
	CourseService courseService = new CourseService();
    // Verify admin session
    String adminEmail = (String) session.getAttribute("adminEmail");
    if (adminEmail == null) {
        response.sendRedirect("login.jsp");
        return;
    }
  
    // Retrieve course code from request
    String code = request.getParameter("code");

    if (code != null) {

        boolean isDeleted = courseService.deleteCourse(code);
        if (isDeleted) {
            out.println("<h3>Course deleted successfully!</h3>");
        } else {
            out.println("<h3>Failed to delete course. Please try again!</h3>");
        }
    } else {
        out.println("<h3>Invalid Course Code!</h3>");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Course</title>
</head>
<body>
    <a href="manage_course.jsp">Back to Course</a>
</body>
</html>
