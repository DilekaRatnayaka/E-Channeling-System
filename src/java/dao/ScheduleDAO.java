package dao;

import util.databaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Schedule;

public class ScheduleDAO {
    
    // Method to get schedules by doctor_id
    public List<Schedule> getSchedulesByDoctorId(String doctorId) throws SQLException, ClassNotFoundException {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM Schedule WHERE doctor_id = ?";

        try (Connection connection = databaseConnection.initializeDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the doctorId parameter in the SQL query
            preparedStatement.setString(1, doctorId);

            // Execute the query and process the result set
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setScheduleId(resultSet.getInt("schedule_id"));
                    schedule.setDoctorId(resultSet.getString("doctor_id"));
                    schedule.setDoctorName(resultSet.getString("doctor_name"));
                    schedule.setChannelingDate(resultSet.getDate("channeling_date"));
                    schedule.setChannelingTime(resultSet.getTime("channeling_time"));
                    schedule.setPatientLimit(resultSet.getInt("patient_limit"));
                    schedules.add(schedule);
                }
            }
        }
        return schedules;
    }
    
    // Method to get all schedules (not filtered by doctor)
    public List<Schedule> getAllSchedules() throws SQLException, ClassNotFoundException {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM Schedule";

        try (Connection connection = databaseConnection.initializeDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleId(resultSet.getInt("schedule_id"));
                schedule.setDoctorId(resultSet.getString("doctor_id"));
                schedule.setDoctorName(resultSet.getString("doctor_name"));
                schedule.setChannelingDate(resultSet.getDate("channeling_date"));
                schedule.setChannelingTime(resultSet.getTime("channeling_time"));
                schedule.setPatientLimit(resultSet.getInt("patient_limit"));
                schedules.add(schedule);
            }
        }
        return schedules;
    }
    
    // Method to update a schedule
    public boolean updateSchedule(Schedule schedule) throws SQLException, ClassNotFoundException {
        String query = "UPDATE Schedule SET channeling_date = ?, channeling_time = ?, patient_limit = ? WHERE schedule_id = ?";
        try (Connection connection = databaseConnection.initializeDatabase();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setDate(1, schedule.getChannelingDate());
            stmt.setTime(2, schedule.getChannelingTime());
            stmt.setInt(3, schedule.getPatientLimit());
            stmt.setInt(4, schedule.getScheduleId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Method to delete a schedule
    public boolean deleteSchedule(int scheduleId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM Schedule WHERE schedule_id = ?";
        try (Connection connection = databaseConnection.initializeDatabase();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, scheduleId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    public Schedule getScheduleById(int scheduleId) {
        Schedule schedule = null;
        String query = "SELECT * FROM Schedule WHERE schedule_id = ?";

        try (Connection connection = databaseConnection.initializeDatabase();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, scheduleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    schedule = new Schedule();
                    schedule.setScheduleId(rs.getInt("schedule_id"));
                    schedule.setDoctorId(rs.getString("doctor_id"));
                    schedule.setDoctorName(rs.getString("doctor_name"));
                    schedule.setChannelingDate(rs.getDate("channeling_date"));
                    schedule.setChannelingTime(rs.getTime("channeling_time"));
                    schedule.setPatientLimit(rs.getInt("patient_limit"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return schedule;
    }
}