package com.nits.controller;

import com.nits.service.UserService;
import com.nits.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Admin credentials check
        String adminEmail = "admin@nits.com";
        String adminPassword = "admin@123";

        if (adminEmail.equals(email) && adminPassword.equals(password)) {
            // Admin login successful
            request.getSession().setAttribute("adminEmail", adminEmail);
            response.sendRedirect("dashboard.jsp");
            return;
        } 
        String inEmail = "anushasv02@gmail.com";
        String inPassword = "111";

        if (inEmail.equals(email) && inPassword.equals(password)) {
            // in login successful
            request.getSession().setAttribute("inEmail", inEmail);
            response.sendRedirect("instructor_dashboard.jsp");
            return;
        }

  
        // Regular user credentials check
        User user = null;
		try {
			user = userService.validateUser(email, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);
            response.sendRedirect("courses.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid email or password.");
            // Login failed
            request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
