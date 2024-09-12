<%-- 
    Document   : registerDoctor
    Created on : Aug 11, 2024, 9:50:41 AM
    Author     : dilek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Doctor Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background: url('image/web3.jpg') no-repeat center center fixed;
            background-size: cover;
        }
        .registration-form {
            background: rgba(255, 255, 255, 0.8); /* Semi-transparent white */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            box-sizing: border-box;
        }
        h2 {
            margin-bottom: 20px;
            color: #333;
            text-align: center;
        }
        input[type="text"], input[type="password"], input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #000; /* Black background */
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #333; /* Darker black on hover */
        }
    </style>
</head>
<body>
    <div class="registration-form">
        <h2>Doctor Registration</h2>
        <form action="DoctorRegistrationServlet" method="post">
            <input type="text" name="userID" placeholder="User ID" required>
            <input type="text" name="name" placeholder="Name" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="text" name="phone" placeholder="Phone" required>
            <input type="text" name="specialization" placeholder="Specialization" required>
            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>

