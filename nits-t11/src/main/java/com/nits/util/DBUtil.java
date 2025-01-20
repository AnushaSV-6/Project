package com.nits.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	  private static final String URL = "jdbc:mysql://localhost:3306/nits_pro";
	    private static final String USER = "root"; // Replace with your MySQL username
	    private static final String PASSWORD = "Mysql@123"; // Replace with your MySQL password
    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new Exception("MySQL Driver not found. Ensure mysql-connector-java.jar is added to your classpath.", e);
        } catch (SQLException e) {
            throw new Exception("Failed to connect to the database. Check the URL, username, and password.", e);
        }
    }
    // Method to close the connection
    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error while closing the connection: " + e.getMessage());
        }
}
}
