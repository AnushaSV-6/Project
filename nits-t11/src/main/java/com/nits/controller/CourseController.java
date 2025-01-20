package com.nits.controller;

import com.nits.model.Course;
import com.nits.model.User;
import com.nits.model.UserCourse;
import com.nits.service.CourseService;
import com.nits.service.UserCourseService;


import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/courses")
public class CourseController extends HttpServlet {

    private CourseService courseService;
    private UserCourseService userCourseService;

    public void init() throws ServletException {
        // Initialize service layers
        courseService = new CourseService();
        userCourseService = new UserCourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the logged-in user from the session
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser == null) {
            // Redirect to login if no user is logged in
            response.sendRedirect("login.jsp");
            return;
        }


        try {
            // Fetch all available courses
            List<Course> courses = courseService.getAllCourses();

            // If the courses list is null, initialize it to an empty list
            if (courses == null) {
                courses = new ArrayList<>();
            }

            // Fetch the courses the user is already enrolled in
            List<UserCourse> enrolledCourses = userCourseService.getEnrolledCourses(loggedInUser.getEmail());

            // If enrolledCourses is null, initialize it to an empty list
            if (enrolledCourses == null) {
                enrolledCourses = new ArrayList<>();
            }

            request.setAttribute("courses", courses);
            request.setAttribute("enrolledCourses", enrolledCourses);
            request.setAttribute("loggedInUser", loggedInUser);

            request.getRequestDispatcher("courses.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving courses or enrolled data");
        }
    
    }
}