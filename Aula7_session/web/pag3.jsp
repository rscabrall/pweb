<%-- 
    Document   : pag3
    Created on : 13/03/2014, 11:40:36
    Author     : ad122193
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page 3</title>
    </head>
    <body>
        <h1>Hello Mazé!</h1>
        <%
            String login = String.valueOf(session.getAttribute("atributo"));
            out.println("Olá "+login);
        %>
    </body>
</html>
