package com.nits.dao;

import com.nits.model.UserCourse;
import com.nits.model.Course;
import com.nits.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
  
    /// for course 
    public  List<Course> getAllCourses() throws Exception {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM course";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String description = rs.getString("description");
                courses.add(new Course(code, name, description)); // Add the course object
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }


    // Method to add a course
    public  boolean addCourse(String code, String name, String description) throws Exception {
        String query = "INSERT INTO course (code, name, description) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, code);
            ps.setString(2, name);
            ps.setString(3, description);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    // Method to delete a course
    public boolean deleteCourse(String code) throws Exception {
        String query = "DELETE FROM course WHERE code = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, code);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update a course
    public boolean updateCourse(String code, String name, String description) throws Exception {
        String query = "UPDATE course SET name = ?, description = ? WHERE code = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, code);

            return preparedStatement.executeUpdate() > 0; // Returns true if at least one row is updated
        }
    }

    public static List<UserCourse> getAllUserCourses() throws Exception {
        List<UserCourse> userCourses = new ArrayList<>();
        String query = "SELECT uc.email, uc.code, c.name AS course_name FROM user_course uc INNER JOIN course c ON uc.code = c.code";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String code = resultSet.getString("code");
                String courseName = resultSet.getString("course_name");

                userCourses.add(new UserCourse(email, code, courseName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userCourses;
    }
  
       

        // Fetch a specific course by its code
        public Course getCourseByCode(String code) throws Exception {
            Course course = null;
            String query = "SELECT * FROM course WHERE code = ?";

            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, code);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        course = new Course(code, name, description);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return course;
        }
    }


