<%-- 
    Document   : index
    Created on : 13/03/2014, 11:38:55
    Author     : ad122193
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form method="POST" action="MatsuServlet">
            <label>Login</label></br>            
            <input type="text" name="login" value="" maxlength="30" /></br></br>            
            <input type="submit" name="Envia" value="Entrar"/>            
            
        </form>
    </body>
</html>
