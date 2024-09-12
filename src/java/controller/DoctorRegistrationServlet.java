/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/DoctorRegistrationServlet"})
public class DoctorRegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String userID = request.getParameter("userID");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String specialization = request.getParameter("specialization");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_channeling", "root", "dile123@Abc@def");

            // Prepare SQL query to insert new doctor
            String sql = "INSERT INTO Doctor (user_id, name, password, phone, specialization) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userID);
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.setString(4, phone);
            stmt.setString(5, specialization);

            // Execute SQL query
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Registration successful, redirect to a success page or login page
                response.sendRedirect("loginDoctor.jsp");
            } else {
                // Registration failed, redirect back to registerDoctor.jsp with an error message
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("registerDoctor.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            request.setAttribute("errorMessage", "Database driver not found.");
            request.getRequestDispatcher("registerDoctor.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("registerDoctor.jsp").forward(request, response);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                // Handle potential errors while closing resources
            }
        }
    }
}