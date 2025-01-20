<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nits.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        out.println("<h3>Error: User session is missing. Please register again.</h3>");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Successful</title>
</head>
<body>
    <h1>Congratulations, <%= user.getName() %>!</h1>
    <p>Your registration was successful.</p>
    <p>We have saved your information:</p>
    <ul>
        <li><strong>Name:</strong> <%= user.getName() %></li>
        <li><strong>Email:</strong> <%= user.getEmail() %></li>
        <li><strong>Phone:</strong> <%= user.getPhone() %></li>
        <li><strong>Gender:</strong> <%= user.getGender() %></li>
        <li><strong>Education:</strong> <%= user.getEducation() %></li>
        <li><strong>Languages:</strong> <%= user.getLanguages() %></li>
        <li><strong>About:</strong> <%= user.getAbout() %></li>
    </ul>
    <form action="login.jsp" method="post">
        <button type="submit">Go to Login</button>
    </form>
</body>
</html>
