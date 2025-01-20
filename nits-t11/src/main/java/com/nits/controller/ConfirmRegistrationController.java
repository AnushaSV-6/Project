package com.nits.controller;

import com.nits.model.User;
import com.nits.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/confirmRegistration")
public class ConfirmRegistrationController extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Avoid creating a new session
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("register.jsp"); // Redirect to registration page
            return;
        }

        try {
            boolean isSaved = userService.saveUser(user);
            if (isSaved) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("congrats.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to save user. Please try again.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while saving the user.");
        }
    }
}
