/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/PatientLoginServlet"})
public class PatientLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String nic = request.getParameter("NIC");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_channeling", "root", "dile123@Abc@def");

            // Prepare SQL query to check credentials
            String sql = "SELECT * FROM Patient WHERE nic = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nic);
            stmt.setString(2, password);

            // Execute query
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Login successful, store user details in session
                HttpSession session = request.getSession();
                session.setAttribute("patientID", rs.getString("patient_id"));
                session.setAttribute("patientName", rs.getString("name"));
                session.setAttribute("patientPhone", rs.getString("phone"));
                session.setAttribute("patientNIC", rs.getString("nic"));
                // Login successful, redirect to manageSchedule.jsp
                response.sendRedirect("patientProfile.jsp");
            } else {
                // Login failed, redirect back to loginPatient.jsp with an error message
                request.setAttribute("errorMessage", "Invalid NIC or password. Please try again.");
                request.getRequestDispatcher("loginPatient.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            request.setAttribute("errorMessage", "Database driver not found.");
            request.getRequestDispatcher("loginPatient.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("loginPatient.jsp").forward(request, response);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                // Handle potential errors while closing resources
            }
        }
    }
}