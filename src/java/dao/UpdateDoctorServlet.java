package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import util.databaseConnection;

@WebServlet("/UpdateDoctorServlet")
public class UpdateDoctorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String specialization = request.getParameter("specialization");
        String userId = (String) request.getSession().getAttribute("userId"); // Assuming you store userId in session

        String updateQuery = "UPDATE Doctor SET phone=?, specialization=? WHERE user_id=?";
        
        try (Connection conn = databaseConnection.initializeDatabase();
                PreparedStatement statement = conn.prepareStatement(updateQuery)) {
            
            statement.setString(1, phone);
            statement.setString(2, specialization);
            statement.setString(3, userId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                // Update session attributes
                request.getSession().setAttribute("doctorPhone", phone);
                request.getSession().setAttribute("doctorSpecialization", specialization);

                response.sendRedirect("doctorProfile.jsp"); // Redirect to doctor's profile page
            } else {
                // Handle failure (optional)
                request.setAttribute("errorMessage", "Failed to update doctor information. Please try again.");
                request.getRequestDispatcher("updateDoctor.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            // Handle errors
            request.setAttribute("errorMessage", "An error occurred while updating the information. Please try again.");
            request.getRequestDispatcher("updateDoctor.jsp").forward(request, response);
        }
    }
}

