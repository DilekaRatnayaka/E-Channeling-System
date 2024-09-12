<%-- 
    Document   : patientProfile
    Created on : Aug 11, 2024, 9:50:20 AM
    Author     : dilek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Patient Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background: url('image/web3.jpg') no-repeat center center fixed; /* Ensure the image doesn't repeat and is centered */
            background-size: cover; /* Cover the entire screen */
        }
        .profile-container {
            background: rgba(255, 255, 255, 0.8); /* Semi-transparent white */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            box-sizing: border-box; /* Ensures padding and border are included in the element's width and height */
            margin: 20px;
            text-align: center;
        }
        h1 {
            margin-bottom: 20px;
            color: #333;
        }
        .update-link {
            display: inline-block;
            background-color: #000; /* Black background */
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 4px;
            margin-top: 20px;
        }
        .update-link:hover {
            background-color: #333; /* Darker black on hover */
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <h1>Patient Profile</h1>
        <p>Welcome, Patient!</p>
        
        <!-- Link to manageSchedule.jsp -->
        <a href="appointment.jsp" class="update-link">Make an Appointment</a>

        <!-- Link to updateDoctor.jsp -->
        <a href="updatePatient.jsp" class="update-link">Update Your Information</a>
     
    </div>
</body>
</html>

