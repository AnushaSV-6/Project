package com.nits.dao;

import com.nits.model.User;
import com.nits.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public boolean checkEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM user WHERE email = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPhoneExists(String phone) {
        String query = "SELECT COUNT(*) FROM user WHERE phone = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, phone);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveUser(User user) {
        if (user == null) {
            System.out.println("User object is null in saveUser method.");
            return false;
        }
        // Proceed with saving the user to the database
        String query = "INSERT INTO user (name, email, phone, password, gender, education, languages, about) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getGender());
            stmt.setString(6, user.getEducation());
            stmt.setString(7, user.getLanguages());
            stmt.setString(8, user.getAbout());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        public User validateUser(String email, String password) throws Exception {
        	 User user = null;
             try (Connection connection = DBUtil.getConnection()) {
                 String sql = "SELECT * FROM User WHERE email = ? AND password = ?";
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 preparedStatement.setString(1, email);
                 preparedStatement.setString(2, password);
                 ResultSet resultSet = preparedStatement.executeQuery();

                 if (resultSet.next()) {
                     user = new User();
                     user.setEmail(resultSet.getString("email"));
                     user.setName(resultSet.getString("name"));
                     user.setPhone(resultSet.getString("phone"));
                     // Populate other fields as required
                 }
             } catch (SQLException e) {
                 e.printStackTrace();
             }
             return user;
         }

     
        public List<User> getAllUsers() {
            List<User> users = new ArrayList<>();
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user");
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    User user = new User();
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setPassword(rs.getString("password"));
                    user.setGender(rs.getString("gender"));
                    user.setEducation(rs.getString("education"));
                    user.setLanguages(rs.getString("languages"));
                    user.setAbout(rs.getString("about"));
                    users.add(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return users;
        }
       
          
            // Fetch a user by email
            public User getUserByEmail(String email) throws Exception {
                User user = null;
                String query = "SELECT * FROM user WHERE email = ?";

                try (Connection conn = DBUtil.getConnection();
                     PreparedStatement ps = conn.prepareStatement(query)) {

                    ps.setString(1, email);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String name = rs.getString("name");
                            user = new User();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return user;
            }
        }

    
    
                
            