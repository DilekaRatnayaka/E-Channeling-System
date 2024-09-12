<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.Schedule" %>
<%@ page import="dao.ScheduleDAO" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Schedule</title>
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
            background-color: rgba(255, 255, 255, 0.8);
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            max-width: 100%;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        label {
            font-weight: bold;
            text-align: left;
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="date"],
        input[type="time"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
            border-radius: 4px;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        a {
            display: inline-block;
            margin-top: 15px;
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s;
        }
        a:hover {
            color: #388e3c;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Schedule</h1>
        <%
            // Retrieve the schedule ID from the request parameter
            String scheduleIdStr = request.getParameter("schedule_id");
            int scheduleId = Integer.parseInt(scheduleIdStr);
            
            // Use ScheduleDAO to get the specific schedule by ID
            ScheduleDAO scheduleDAO = new ScheduleDAO();
            Schedule schedule = scheduleDAO.getScheduleById(scheduleId);
            
            if (schedule == null) {
                out.println("<p>Schedule not found.</p>");
            } else {
                // Retrieve values separately
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                
                String channelingDate = dateFormat.format(schedule.getChannelingDate());
                String channelingTime = timeFormat.format(schedule.getChannelingTime());
                int patientLimit = schedule.getPatientLimit();
        %>
        
        <!-- Now use these values to fill the form -->
        
        <form action="ScheduleServlet" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="schedule_id" value="<%= schedule.getScheduleId() %>">

            <label for="doctor_name">Doctor Name:</label>
            <input type="text" id="doctor_name" name="doctor_name" value="<%= schedule.getDoctorName() %>" readonly>

            <label for="channeling_date">Channeling Date:</label>
            <input type="date" id="channeling_date" name="channeling_date" value="<%= channelingDate %>" required>

            <label for="channeling_time">Channeling Time:</label>
            <input type="time" id="channeling_time" name="channeling_time" value="<%= channelingTime %>" step="1" required>

            <label for="patient_limit">Number of Patients:</label>
            <input type="number" id="patient_limit" name="patient_limit" value="<%= patientLimit %>" required>
        
            <input type="submit" value="Update">
        </form>
        
        <a href="manageScheduleServlet">Back to Schedule List</a>
        
        <%
            }
        %>
    </div>
</body>
</html>
