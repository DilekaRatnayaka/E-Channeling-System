/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dao;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.databaseConnection;

@WebServlet(urlPatterns = {"/UpdatePatientServlet"})
public class UpdatePatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String nic = (String) request.getSession().getAttribute("patientNIC"); 

        String updateQuery = "UPDATE Patient SET phone=? WHERE nic=?";
        
        try (Connection conn = databaseConnection.initializeDatabase();
                PreparedStatement statement = conn.prepareStatement(updateQuery)) {
            
            statement.setString(1, phone);
            statement.setString(2, nic);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                // Update session attributes
                request.getSession().setAttribute("patientPhone", phone);

                response.sendRedirect("patientProfile.jsp"); 
            } else {
                // Handle failure (optional)
                request.setAttribute("errorMessage", "Failed to update doctor information. Please try again.");
                request.getRequestDispatcher("updatePatient.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            // Handle errors
            request.setAttribute("errorMessage", "An error occurred while updating the information. Please try again.");
            request.getRequestDispatcher("updatePatient.jsp").forward(request, response);
        }
    }
}

