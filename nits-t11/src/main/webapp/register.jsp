<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nits.model.User" %>
<%
    // Retrieve the User object from the session (if available)
    User user = (User) session.getAttribute("user");
    if (user == null) {
        user = new User(); // Initialize if not present
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h1>Register</h1>
    <form action="registerController" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="<%= user.getName() != null ? user.getName() : "" %>" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= user.getEmail() != null ? user.getEmail() : "" %>" required><br>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="<%= user.getPhone() != null ? user.getPhone() : "" %>" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password"  value="<%= user.getPassword() != null ? user.getPassword() : "" %>" required><br>


        <label for="education">Education:</label>
        <select id="education" name="education" required>
            <option value="">Select Education</option>
            <option value="Diploma" <%= "Diploma".equals(user.getEducation()) ? "selected" : "" %>>Diploma</option>
            <option value="BSc" <%= "BSc".equals(user.getEducation()) ? "selected" : "" %>>BSc</option>
            <option value="BCA" <%= "BCA".equals(user.getEducation()) ? "selected" : "" %>>BCA</option>
            <option value="Engineering" <%= "Engineering".equals(user.getEducation()) ? "selected" : "" %>>Engineering</option>
        </select><br>

        <label>Languages Known:</label>
        <input type="checkbox" name="languages" value="Java" <%= user.getLanguages() != null && user.getLanguages().contains("Java") ? "checked" : "" %>>Java
        <input type="checkbox" name="languages" value="C++" <%= user.getLanguages() != null && user.getLanguages().contains("C++") ? "checked" : "" %>>C++
        <input type="checkbox" name="languages" value="Python" <%= user.getLanguages() != null && user.getLanguages().contains("Python") ? "checked" : "" %>>Python<br>

        <label>Gender:</label>
        <input type="radio" name="gender" value="Male" <%= "Male".equals(user.getGender()) ? "checked" : "" %>>Male
        <input type="radio" name="gender" value="Female" <%= "Female".equals(user.getGender()) ? "checked" : "" %>>Female<br>

        <label for="about">About Yourself:</label>
        <textarea id="about" name="about"><%= user.getAbout() != null ? user.getAbout() : "" %></textarea><br>

        <button type="submit">Submit</button>
    </form>
</body>
</html>
