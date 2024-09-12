package dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;


import model.Schedule;

@WebServlet(name = "ScheduleServlet", urlPatterns = {"/ScheduleServlet"})
public class ScheduleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateSchedule(request, response);
        }
    }

    private void updateSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int scheduleId = Integer.parseInt(request.getParameter("schedule_id"));
        Date channelingDate = Date.valueOf(request.getParameter("channeling_date"));
        Time channelingTime = Time.valueOf(request.getParameter("channeling_time"));
        int patientLimit = Integer.parseInt(request.getParameter("patient_limit"));

        Schedule schedule = new Schedule();
        schedule.setScheduleId(scheduleId);
        schedule.setChannelingDate(channelingDate);
        schedule.setChannelingTime(channelingTime);
        schedule.setPatientLimit(patientLimit);

        ScheduleDAO scheduleDAO = new ScheduleDAO();
        try {
            boolean isUpdated = scheduleDAO.updateSchedule(schedule);
            if (isUpdated) {
                response.sendRedirect("manageSchedule.jsp");  // Redirect to a page showing all schedules
            } else {
                response.getWriter().write("Failed to update schedule.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}

    