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

@WebServlet(urlPatterns = {"/DoctorLoginServlet"})
public class DoctorLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String userID = request.getParameter("userID");
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
            String sql = "SELECT * FROM Doctor WHERE user_id = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userID);
            stmt.setString(2, password);

            // Execute query
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Login successful, store user details in session
                HttpSession session = request.getSession();
                session.setAttribute("doctorId", userID);
                session.setAttribute("doctorName", rs.getString("name"));
                session.setAttribute("doctorPhone", rs.getString("phone"));
                session.setAttribute("doctorSpecialization", rs.getString("specialization"));

                // Login successful, redirect to doctorProfile.jsp
                response.sendRedirect("doctorProfile.jsp");
            } else {
                // Login failed, redirect back to loginDoctor.jsp with an error message
                request.setAttribute("errorMessage", "Invalid User ID or password. Please try again.");
                request.getRequestDispatcher("loginDoctor.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            request.setAttribute("errorMessage", "Database driver not found.");
            request.getRequestDispatcher("loginDoctor.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("loginDoctor.jsp").forward(request, response);
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
