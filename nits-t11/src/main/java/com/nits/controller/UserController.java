package com.nits.controller;

import com.nits.model.User;
import com.nits.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UserController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(); // Initialize the service layer
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("register.jsp");
            return;
        }

        switch (action) {
            case "register":
                handleRegistration(request, response);
                break;
            case "confirm":
                handleConfirmation(request, response);
                break;
            default:
                response.sendRedirect("register.jsp");
        }
    }

    private void handleRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String education = request.getParameter("education");
        String[] languages = request.getParameterValues("languages");
        String about = request.getParameter("about");

        // Create User object
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setGender(gender);
        user.setEducation(education);
        user.setLanguages(String.join(", ", languages != null ? languages : new String[0]));
        user.setAbout(about);

        // Check if email already exists
        if (userService.isEmailExists(email)) {
            request.setAttribute("errorMessage", "The email '" + email + "' is already registered.");
            request.setAttribute("user", user); // Keep user data for re-population
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Check if phone number already exists
        if (userService.isPhoneExists(phone)) {
            request.setAttribute("errorMessage", "The phone number '" + phone + "' is already registered.");
            request.setAttribute("user", user); // Keep user data for re-population
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Store user in session and forward to check.jsp
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        request.getRequestDispatcher("check.jsp").forward(request, response);
    }

    private void handleConfirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the User object from session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            request.setAttribute("errorMessage", "User session is missing. Please register again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Save the user using registerUser method
        boolean isSaved = userService.registerUser(user);

        if (isSaved) {
            session.removeAttribute("user");
            request.getRequestDispatcher("congrats.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Error saving user to the database. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
