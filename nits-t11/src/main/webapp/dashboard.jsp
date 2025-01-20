<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.TimeZone" %>
<%
    // Verify admin session
    String adminEmail = (String) session.getAttribute("adminEmail");
    if (adminEmail == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String adminName = adminEmail.contains("@") ? adminEmail.substring(0, adminEmail.indexOf("@")) : adminEmail;

    // Determine the current time of day
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    String greeting;

    if (hour < 12) {
        greeting = "Good Morning";
    } else if (hour < 17) {
        greeting = "Good Afternoon";
    } else {
        greeting = "Good Evening";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            text-align: center;
            padding: 50px;
            background-color: white;
            border-radius: 8px;
            margin: 20px auto;
            width: 80%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .container h1 {
            color: #333;
            font-size: 36px;
        }
        .container p {
            color: #555;
            font-size: 18px;
        }
        .course-box {
            width: 30%;
            display: inline-block;
            margin: 20px;
            padding: 20px;
            border: 2px solid #ccc;
            border-radius: 8px;
            background-color: #fff;
            text-align: center;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .course-box:hover {
            transform: translateY(-10px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }
        .course-box h2 {
            color: #333;
            font-size: 22px;
        }
        .course-box p {
            color: #666;
        }
        .course-box .btn-register {
            background-color: #0066cc;
            color: white;
            border: none;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
            display: inline-block;
        }
        .course-box .btn-register:hover {
            background-color: #004c99;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the Admin Dashboard</h1>
        <p><%= greeting %>, <b><%= adminName %></b>!</p>
        <p>Select a section below to manage Users, Courses, User-Course Enrollments, or Instructors.</p>

        <!-- Dashboard Options -->
        <div class="course-box">
            <h2>Manage Users</h2>
            <p>View and manage all registered users.</p>
          <form action="manageUsers" method="get">
  <a href="manage_users.jsp" class="btn-register">Let's Go!</a></form>
          
        </div>

        <div class="course-box">
            <h2>Manage Courses</h2>
            <p>Create, edit, and delete courses offered on the platform.</p>
            <a href="manage_course.jsp" class="btn-register">Let's Go!</a>
        </div>

        <div class="course-box">
            <h2>User-Course Enrollments</h2>
            <p>View and manage user enrollments in courses.</p>
            <a href="user_courses.jsp" class="btn-register">Let's Go!</a>
        </div>

        
    </div>
</body>
</html>
