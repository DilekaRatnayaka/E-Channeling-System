package dao;

import model.Appointment;
import model.Schedule;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookAppointment")
public class AppointmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        int patientId = Integer.parseInt(request.getParameter("patientId")); // Assume patientId is passed from the session or request

        AppointmentDAO appointmentDAO = new AppointmentDAO();
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        Schedule schedule = scheduleDAO.getScheduleById(scheduleId);

        boolean canBook = appointmentDAO.checkPatientLimit(scheduleId, schedule.getPatientLimit());

        if (canBook) {
            Appointment appointment = new Appointment();
            appointment.setScheduleId(scheduleId);
            appointment.setPatientId(patientId);
            
            boolean success = appointmentDAO.bookAppointment(appointment);
            if (success) {
                response.sendRedirect("appointment.jsp?success=true");
            } else {
                response.sendRedirect("appointment.jsp?error=true");
            }
        } else {
            response.sendRedirect("appointment.jsp?limitExceeded=true");
        }
    }
}
