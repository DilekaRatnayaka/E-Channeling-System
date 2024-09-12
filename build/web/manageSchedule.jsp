<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Schedule" %>
<%@ page import="dao.ScheduleDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Schedule</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            padding: 20px;
            background: url('image/web3.jpg') no-repeat center center fixed;
            background-size: cover;
        }
        .container {
            background-color: rgba(255, 255, 255, 0.8);
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 800px;
            margin-top: 20px;
        }
        h1, h2 {
            color: #333;
            text-align: center;
        }
        p {
            color: red;
            text-align: center;
        }
        form {
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }
        input[type="date"],
        input[type="time"],
        input[type="number"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 4px;
            font-size: 16px;
            transition: background-color 0.3s;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        td form {
            display: inline-block;
        }
        td form input[type="submit"] {
            width: auto;
            padding: 5px 10px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Manage Schedule</h1>

        <% String errorMessage = (String) request.getAttribute("errorMessage");
           if (errorMessage != null) { %>
            <p><%= errorMessage %></p>
        <% } %>

        <form action="manageScheduleServlet" method="post">
            <label for="channeling_date">Channeling Date:</label>
            <input type="date" id="channeling_date" name="channeling_date" required>

            <label for="channeling_time">Channeling Time:</label>
            <input type="time" id="channeling_time" name="channeling_time" required>

            <label for="patient_limit">Number of Patients:</label>
            <input type="number" id="patient_limit" name="patient_limit" required>

            <input type="submit" value="Submit">
        </form>

        <h2>Your Scheduled Channeling Sessions</h2>
        <% List<Schedule> doctorSchedules = (List<Schedule>) request.getAttribute("doctorSchedules");
           if (doctorSchedules != null && !doctorSchedules.isEmpty()) { %>
            <table>
                <tr>
                    <th>Channeling Date</th>
                    <th>Channeling Time</th>
                    <th>Patient Limit</th>
                    <th>Actions</th>
                </tr>
                <% for (Schedule schedule : doctorSchedules) { %>
                    <tr>
                        <td><%= schedule.getChannelingDate() %></td>
                        <td><%= schedule.getChannelingTime() %></td>
                        <td><%= schedule.getPatientLimit() %></td>
                        <td>
                            <form action="editSchedule.jsp" method="post">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="schedule_id" value="<%= schedule.getScheduleId() %>">
                                <input type="submit" value="Edit">
                            </form>
                            <form action="manageScheduleServlet" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="schedule_id" value="<%= schedule.getScheduleId() %>">
                                <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this schedule?');">
                            </form>
                        </td>
                    </tr>
                <% } %>
            </table>
        <% } else { %>
            <p>No scheduled channeling sessions found.</p>
        <% } %>
    </div>
</body>
</html>

