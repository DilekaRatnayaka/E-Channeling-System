<%-- 
    Document   : updateDoctor
    Created on : Aug 11, 2024, 9:51:13 AM
    Author     : dilek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Doctor Information</title>
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
        .form-container {
            background: rgba(255, 255, 255, 0.8); /* Semi-transparent white */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            box-sizing: border-box; /* Ensures padding and border are included in the element's width and height */
            margin: 20px;
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
            box-sizing: border-box; /* Ensures padding and border are included in the element's width and height */
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
    <script type="text/javascript">
        function showError(message) {
            alert(message); // Popup message
            document.getElementById("phone").value = ""; // Clear the phone field
            document.getElementById("specialization").value = ""; // Clear the specialization field
        }
    </script>
</head>
<body>
    <div class="form-container">
        <h2>Update Doctor Information</h2>
        <form action="UpdateDoctorServlet" method="post">
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" value="<%= session.getAttribute("doctorPhone") %>" required><br><br>
        
            <label for="specialization">Specialization:</label>
            <input type="text" id="specialization" name="specialization" value="<%= session.getAttribute("doctorSpecialization") %>" required><br><br>
        
            <input type="submit" value="Update">
        </form>
    </div>
    
    <!-- Display the error message if present -->
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
    <script type="text/javascript">
        showError("<%= errorMessage %>");
    </script>
    <%
        }
    %>
</body>
</html>
