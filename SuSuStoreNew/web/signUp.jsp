<%-- 
    Document   : signUp
    Created on : Mar 16, 2025, 11:56:18 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel='stylesheet' href="./css/signUp.css">
    </head>
    <body>

        <div class="signup-container">
            <h2>SignUP</h2>
            <form action="/MainController" method="POST">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="fullName">FullName:</label>
                    <input type="text" id="fullName" name="fullName">
                </div>
                <div class="form-group">
                    <label for="phoneNumber">PhoneNumber:</label>
                    <input type="tel" id="phoneNumber" name="phoneNumber" required>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" required>
                </div>
                <button type="submit">SignUp</button>
            </form>
        </div>
    </body>
</html>
