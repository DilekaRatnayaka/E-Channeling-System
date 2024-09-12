<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Patient Login</title>
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
        .login-form {
            background: rgba(255, 255, 255, 0.9); /* Semi-transparent white */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            margin: 20px;
            box-sizing: border-box; /* Ensures padding and border are included in the element's width and height */
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
</head>
<body>
    <div class="login-form">
        <h2>Patient Login</h2>
        <form action="PatientLoginServlet" method="post">
            <input type="text" name="NIC" placeholder="NIC" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="submit" value="Login">
        </form>
    </div>
</body>
</html>