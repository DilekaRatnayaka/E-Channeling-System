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

@WebServlet(urlPatterns = {"/PatientRegistrationServlet"})
public class PatientRegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String nic = request.getParameter("NIC");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_channeling", "root", "dile123@Abc@def");

            // Prepare SQL query
            String sql = "INSERT INTO Patient (nic, name, phone, password) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nic);
            stmt.setString(2, name);
            stmt.setString(3, phone);
            stmt.setString(4, password);

            // Execute SQL query
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Registration successful, redirect to login.jsp
                response.sendRedirect("loginPatient.jsp");
            } else {
                // Registration failed, redirect back to registerPatient.jsp with an error message
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("registerPatient.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            request.setAttribute("errorMessage", "Database driver not found.");
            request.getRequestDispatcher("registerPatient.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("registerPatient.jsp").forward(request, response);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            }
        }
    }
}