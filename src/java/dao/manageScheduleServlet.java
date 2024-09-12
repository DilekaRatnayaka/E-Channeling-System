package dao;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Schedule;
import util.databaseConnection;

@WebServlet(name = "manageScheduleServlet", urlPatterns = {"/manageScheduleServlet"})
public class manageScheduleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Method to fetch schedules for a specific doctor
    private List<Schedule> getDoctorSchedules(String doctorId) throws SQLException, ClassNotFoundException {
        List<Schedule> schedules = new ArrayList<>();

        String query = "SELECT schedule_id, doctor_name, channeling_date, channeling_time, patient_limit FROM Schedule WHERE doctor_id = ?";
        try (Connection connection = databaseConnection.initializeDatabase();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, doctorId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setScheduleId(rs.getInt("schedule_id"));
                    schedule.setDoctorName(rs.getString("doctor_name"));
                    schedule.setChannelingDate(rs.getDate("channeling_date"));
                    schedule.setChannelingTime(rs.getTime("channeling_time"));
                    schedule.setPatientLimit(rs.getInt("patient_limit"));
                    schedules.add(schedule);
                }
            }
        }

        return schedules;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the doctor's ID from the session
        HttpSession session = request.getSession();
        String doctorId = (String) session.getAttribute("doctorId");
        if (doctorId == null) {
            // If the doctor is not logged in, redirect to the login page
            response.sendRedirect("loginDoctor.jsp");
            return;
        }
        try {
            // Fetch the doctor's schedules
            List<Schedule> doctorSchedules = getDoctorSchedules(doctorId);
            // Set the schedules as a request attribute
            request.setAttribute("doctorSchedules", doctorSchedules);
            // Forward the request to manageSchedule.jsp
            request.getRequestDispatcher("manageSchedule.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while retrieving schedules.");
            request.getRequestDispatcher("manageSchedule.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the action parameter to determine whether to update or delete
        String action = request.getParameter("action");

        // Get the doctor's information from the session
        HttpSession session = request.getSession();
        String doctorId = (String) session.getAttribute("doctorId");
        String doctorName = (String) session.getAttribute("doctorName");

        if (doctorId == null || doctorName == null) {
            response.sendRedirect("loginDoctor.jsp");
            return;
        }

        try (Connection connection = databaseConnection.initializeDatabase()) {
            if ("delete".equals(action)) {
                // Delete schedule
                int scheduleId = Integer.parseInt(request.getParameter("schedule_id"));
                String deleteQuery = "DELETE FROM Schedule WHERE schedule_id = ?";
                try (PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {
                    deleteStmt.setInt(1, scheduleId);
                    deleteStmt.executeUpdate();
                }
                response.sendRedirect("manageScheduleServlet");

            } else if ("edit".equals(action)) {
                // Forward to edit form with pre-filled values (optional step)
                int scheduleId = Integer.parseInt(request.getParameter("schedule_id"));
                Schedule schedule = null;
                for (Schedule s : getDoctorSchedules(doctorId)) {
                    if (s.getScheduleId() == scheduleId) {
                        schedule = s;
                        break;
                    }
                }
                request.setAttribute("editSchedule", schedule);
                request.getRequestDispatcher("editSchedule.jsp").forward(request, response);

            } else if ("update".equals(action)) {
                // Update existing schedule
                int scheduleId = Integer.parseInt(request.getParameter("schedule_id"));
                String channelingDate = request.getParameter("channeling_date");
                String channelingTime = request.getParameter("channeling_time");
                int patientLimit = Integer.parseInt(request.getParameter("patient_limit"));

                String updateQuery = "UPDATE Schedule SET channeling_date = ?, channeling_time = ?, patient_limit = ? WHERE schedule_id = ?";
                try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                    updateStmt.setString(1, channelingDate);
                    updateStmt.setString(2, channelingTime);
                    updateStmt.setInt(3, patientLimit);
                    updateStmt.setInt(4, scheduleId);
                    updateStmt.executeUpdate();
                }
                response.sendRedirect("manageScheduleServlet");

            } else {
                // Handle new schedule addition (existing code)
                String channelingDate = request.getParameter("channeling_date");
                String channelingTime = request.getParameter("channeling_time");
                int patientLimit = Integer.parseInt(request.getParameter("patient_limit"));

                // Check if a schedule already exists for this doctor on the given date
                String checkQuery = "SELECT * FROM Schedule WHERE doctor_id = ? AND channeling_date = ?";
                try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                    checkStmt.setString(1, doctorId);
                    checkStmt.setString(2, channelingDate);
                    try (ResultSet rs = checkStmt.executeQuery()) {
                        if (rs.next()) {
                            // Schedule already exists, send error message
                            request.setAttribute("errorMessage", "A schedule already exists for this date.");
                            request.getRequestDispatcher("manageSchedule.jsp").forward(request, response);
                            return;
                        }
                    }
                }

                // Insert the schedule into the database
                String insertQuery = "INSERT INTO Schedule (doctor_id, doctor_name, channeling_date, channeling_time, patient_limit) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, doctorId);
                    insertStmt.setString(2, doctorName);
                    insertStmt.setString(3, channelingDate);
                    insertStmt.setString(4, channelingTime);
                    insertStmt.setInt(5, patientLimit);
                    insertStmt.executeUpdate();
                }

                // After insertion, redirect to doGet to fetch and display updated schedules
                response.sendRedirect("manageScheduleServlet");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing your request. Please try again.");
            request.getRequestDispatcher("manageSchedule.jsp").forward(request, response);
        }
    }
}

