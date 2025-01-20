package com.nits.controller;

import com.nits.model.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerController")
public class RegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String education = request.getParameter("education");
        String[] languageArray = request.getParameterValues("languages");
        String about = request.getParameter("about");

        // Convert languages array to a single comma-separated string
        String languages = String.join(", ", languageArray != null ? languageArray : new String[0]);

        // Create a User object
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setGender(gender);
        user.setEducation(education);
        user.setLanguages(languages);
        user.setAbout(about);

        // Store user object in session for later use
        request.getSession().setAttribute("user", user);

        // Forward to check.jsp for user review
        request.getRequestDispatcher("check.jsp").forward(request, response);
    }
}
