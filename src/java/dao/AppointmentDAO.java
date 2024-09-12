package dao;

import model.Appointment;
import util.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDAO {

    public boolean checkPatientLimit(int scheduleId, int patientLimit) {
        String query = "SELECT COUNT(*) AS count FROM Appointment WHERE schedule_id = ?";
        try (Connection conn = databaseConnection.initializeDatabase();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, scheduleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int currentCount = rs.getInt("count");
                return currentCount < patientLimit;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean bookAppointment(Appointment appointment) {
        String query = "INSERT INTO Appointment (schedule_id, patient_id) VALUES (?, ?)";
        try (Connection conn = databaseConnection.initializeDatabase();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, appointment.getScheduleId());
            ps.setInt(2, appointment.getPatientId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
