<%-- 
    Document   : login.jsp
    Created on : Mar 16, 2025, 10:47:05 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/login.css"
    </head>
    <body>
        <div class="login-container">
            <h2 class="title">Login</h2>
            <form action="MainController" method="POST">
                <div class="username">
                    <label for="username">Username:</label>
                    <input id="username" name="username" type="text" value="ad" required> 
                </div>
                <div class="password">
                    <label for="password">Password:</label>
                    <input id="password" name="password" type="password" value="1" required> 
                </div>
                <c:set var="msg" value="${requestScope.ERROR}"  />
                ${msg}
                <div class="login">
                    <input name="action" value="LOGIN" type="submit">
                </div>
                
            </form>
        </div>
    </body>
</html>
