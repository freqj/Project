<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <%@ include file="header.jsp" %>
    <a href="login" > login </a>
    <a href="register">register</a>
    <a href="userpage">userpage</a>
    <form action="/Controller" method="get">
       <input type="hidden" name="command" value="logout"/>
       <button type="submit"> logout </button>
    </form>
    
   
    <%@ include file="footer.jsp" %>
</body>
</html>