<%-- 
    Document   : index
    Created on : 15/06/2014, 22:07:29
    Author     : Rafael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <jsp:include page="${sessionScope.menu}"/>
    </head>
    <body>
        <h1>Trabalho Final PWeb</h1>
        ${sessionScope.perfil}
    </body>
</html>
