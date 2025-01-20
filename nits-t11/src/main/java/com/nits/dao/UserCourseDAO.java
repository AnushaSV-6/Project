package com.nits.dao;

import com.nits.model.UserCourse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCourseDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/nits_pro";
    private static final String USER = "root";
    private static final String PASSWORD = "Mysql@123";

    // Fetch all courses a user has enrolled in
    public List<UserCourse> getEnrolledCourses(String email) {
        List<UserCourse> enrolledCourses = new ArrayList<>();
        String query = "SELECT * FROM user_course WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String code = rs.getString("code");
                    String courseName = rs.getString("coursename");
                    enrolledCourses.add(new UserCourse(email, code, courseName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrolledCourses;
    }

  

        public boolean saveEnrollment(String email, String code, String courseName) {
            String query = "INSERT INTO user_course (email, code, coursename) VALUES (?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, email);
                ps.setString(2, code);
                ps.setString(3, courseName);

                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        public boolean isUserEnrolled(String email, String code) {
            String query = "SELECT 1 FROM user_course WHERE email = ? AND code = ?";

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, email);
                ps.setString(2, code);

                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next(); // Return true if a record exists
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
    
}
