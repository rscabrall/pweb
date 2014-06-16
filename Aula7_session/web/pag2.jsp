<%-- 
    Document   : pag2
    Created on : 13/03/2014, 11:40:09
    Author     : ad122193
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page 2</title>
    </head>
    <body>
        <h1>Hello Matsu!</h1>
        <!--Pegar o atributo de sessao -->
        <%
            String login = String.valueOf(session.getAttribute("atributo"));
            out.println("Olá "+login);
        %>
    </body>
</html>
