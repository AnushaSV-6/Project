<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nits.model.User" %>
<%@ page import="com.nits.service.UserService" %>

<%
    UserService userService = new UserService();

    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String password = request.getParameter("password");
    String gender = request.getParameter("gender");
    String education = request.getParameter("education");
    String[] languageArray = request.getParameterValues("languages");
    String about = request.getParameter("about");

    String languages = String.join(", ", languageArray != null ? languageArray : new String[0]);

    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPhone(phone);
    user.setPassword(password);
    user.setGender(gender);
    user.setEducation(education);
    user.setLanguages(languages);
    user.setAbout(about);


    boolean emailExists = userService.isEmailExists(email);
    boolean phoneExists = userService.isPhoneExists(phone);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Check Details</title>
</head>
<body>
    <% if (emailExists || phoneExists) {
        %>
        <!-- Display error if email or phone already exists -->
        <h2 style="color: red;">Error: User already exists</h2>
        <p>
            <% if (emailExists) { %>
                Email <strong><%= email %></strong> already exists.<br>
            <% } %>
            <% if (phoneExists) { %>
                Phone <strong><%= phone %></strong> already exists.<br>
            <% } %>
        </p>
    <% } else { %>
        <h2>Check Your Details</h2>
        <ul>
            <li><strong>Name:</strong> <%= name %></li>
            <li><strong>Email:</strong> <%= email %></li>
            <li><strong>Phone:</strong> <%= phone %></li>
            <li><strong>Gender:</strong> <%= gender %></li>
            <li><strong>Education:</strong> <%= education %></li>
            <li><strong>Languages Known:</strong> <%= languages %></li>
            <li><strong>About:</strong> <%= about %></li>
        </ul>
         <form action="register.jsp" method="post">
        <button type="submit">Edit</button>
    </form>
    <form action="confirmRegistration" method="post">
        <button type="submit">Confirm</button>
    <% } %>
</body>
</html>
