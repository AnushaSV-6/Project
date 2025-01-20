package com.nits.service;
import java.util.List;
import com.nits.model.User;
import com.nits.util.DBUtil;

import com.nits.dao.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserService {
    public boolean saveUser(User user) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO user (name, email, phone, password, education, languages, gender, about) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEducation());
            statement.setString(6, user.getLanguages());
            statement.setString(7, user.getGender());
            statement.setString(8, user.getAbout());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public UserDAO userDAO = new UserDAO();

    public boolean isEmailExists(String email) {
        return userDAO.checkEmailExists(email);
    }

    public boolean isPhoneExists(String phone) {
        return userDAO.checkPhoneExists(phone);
    }

    public boolean registerUser(User user) {
        return userDAO.saveUser(user);
 
   }
    public User validateUser(String email, String password) throws Exception {
        return userDAO.validateUser(email, password);
    }
    public List<User> getAllUsers() {
    	return userDAO.getAllUsers();
    }
    public User getUserByEmail(String email) throws Exception {
        return userDAO.getUserByEmail(email);
    }
    }
    


