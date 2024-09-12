package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
    // URL of the MySQL database
    private static final String URL = "jdbc:mysql://localhost:3306/e_channeling";
    // MySQL username
    private static final String USER = "root";  // Change to your MySQL username if different
    // MySQL password
    private static final String PASSWORD = "dile123@Abc@def";  // Replace with your MySQL password

    // Method to initialize and return the database connection
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Establish and return the connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Attempt to establish a connection
            connection = initializeDatabase();
            // Confirm the connection
            if (connection != null) {
                System.out.println("Connection established successfully!");
            }
        } catch (SQLException | ClassNotFoundException e) {
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}