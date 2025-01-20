package com.nits.controller;

import com.nits.model.User;
import com.nits.service.UserCourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/enroll")
public class EnrollController extends HttpServlet {
    private UserCourseService userCourseService;

    public void init() {
        userCourseService = new UserCourseService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	User loggedInUser = (User) session.getAttribute("loggedInUser");

    	if (loggedInUser == null || loggedInUser.getEmail() == null) {
    	    response.sendRedirect("login.jsp");
    	    return;
    	}


        String email = loggedInUser.getEmail();
        String code = request.getParameter("code");
        String courseName = request.getParameter("coursename");

        
            if (userCourseService.isUserEnrolled(email, code)) {
                request.setAttribute("message", "You are already enrolled in this course!");
            } else {
                boolean isEnrolled = userCourseService.enrollUserInCourse(email, code, courseName);
                if (isEnrolled) {
                    request.setAttribute("message", "Enrollment successful!");
                } else {
                    request.setAttribute("message", "Enrollment failed. Please try again.");
                }
            }
            request.getRequestDispatcher("enrollCourse.jsp").forward(request, response);
        
        
    }
}
