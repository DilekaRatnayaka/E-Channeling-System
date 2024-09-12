<%@ page import="java.util.List" %>
<%@ page import="model.Schedule" %>
<%@ page import="dao.ScheduleDAO" %>
<%@ page import="dao.AppointmentDAO" %>
<%@ page import="model.Appointment" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Available Channeling Schedules</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: url('image/web3.jpg') no-repeat center center fixed;
            background-size: cover;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        td form {
            text-align: center;
        }
        td form input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 8px 16px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            cursor: pointer;
            border-radius: 4px;
        }
        td form input[type="submit"]:hover {
            background-color: #45a049;
        }
        .message {
            margin-top: 10px;
            text-align: center;
            padding: 10px;
            border-radius: 4px;
        }
        .message.success {
            background-color: #d4edda;
            color: #155724;
        }
        .message.error {
            background-color: #f8d7da;
            color: #721c24;
        }
    </style>
    <script type="text/javascript">
        function confirmBooking(form) {
            var confirmation = confirm("Are you sure you want to book this appointment?");
            if (confirmation) {
                form.submit();
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Available Channeling Schedules</h1>
        <% if (request.getParameter("success") != null) { %>
            <div class="message success">Appointment booked successfully!</div>
        <% } else if (request.getParameter("error") != null) { %>
            <div class="message error">Error booking appointment. Please try again.</div>
        <% } else if (request.getParameter("limitExceeded") != null) { %>
            <div class="message error">Patient limit exceeded for this schedule. The slot is already filled.</div>
        <% } %>
        <table>
            <thead>
                <tr>
                    <th>Doctor ID</th>
                    <th>Doctor Name</th>
                    <th>Channeling Date</th>
                    <th>Channeling Time</th>
                    <th>Patient Limit</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% ScheduleDAO scheduleDAO = new ScheduleDAO();
                   List<Schedule> schedules = scheduleDAO.getAllSchedules();
                   for (Schedule schedule : schedules) { %>
                    <tr>
                        <td><%= schedule.getDoctorId() %></td>
                        <td><%= schedule.getDoctorName() %></td>
                        <td><%= schedule.getChannelingDate() %></td>
                        <td><%= schedule.getChannelingTime() %></td>
                        <td><%= schedule.getPatientLimit() %></td>
                        <td>
                            <form action="bookAppointment" method="post">
                                <input type="hidden" name="scheduleId" value="<%= schedule.getScheduleId() %>" />
                                <input type="hidden" name="patientId" value="<%= session.getAttribute("patientID") %>" />
                                <input type="submit" value="Book Appointment" onclick="return confirmBooking(this.form)" />
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
