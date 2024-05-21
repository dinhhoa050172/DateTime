<%-- 
    Document   : index
    Created on : 21-May-2024, 10:42:28
    Author     : lamph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="checker.css">
    </head>
    <body>
        <div class="content">

            <form method="get" action="MainController">
                <h1>Date Time Checker</h1>
                <label class="key">Day:</label> <input type="number" placeholder="Input Day..." name="day">
                <br><!-- comment -->
                <label class="key">Month: </label>  <input type="number" placeholder="Input Month..." name="month">
                <br><!-- comment -->
                <label class="key">Year: </label>  <input type="number" placeholder="Input Year..." name="year">
                <br><!-- comment -->
                <input type="submit" value="Check" name="action">
                <input type="submit" value="Clear" name="action">
            </form>

            <%
                String message = (String) request.getAttribute("MESSAGE");
                if (message == null) {
                    message = "";
                }
            %>
            <%= message%>
        </div>
    </body>
</html>
