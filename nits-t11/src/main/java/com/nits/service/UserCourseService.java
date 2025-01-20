package com.nits.service;

import com.nits.dao.UserCourseDAO;
import com.nits.model.UserCourse;

import java.util.List;

public class UserCourseService {
    private UserCourseDAO userCourseDAO;

    public UserCourseService() {
        this.userCourseDAO = new UserCourseDAO();
    }

    

    public List<UserCourse> getEnrolledCourses(String email) {
        return userCourseDAO.getEnrolledCourses(email);
    }

    public boolean isUserEnrolled(String email, String courseCode) {
        return userCourseDAO.isUserEnrolled(email, courseCode);
    }

    public boolean enrollUserInCourse(String email, String courseCode, String courseName) {
        return userCourseDAO.saveEnrollment(email, courseCode, courseName);
    }
}
