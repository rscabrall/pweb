<%-- 
    Document   : index
    Created on : 15/05/2014, 09:51:55
    Author     : AD122176
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Matrícula - Login</title>
    </head>
    <body>
        <h1>Sistema de Matrícula - Login</h1>
        <form method="POST" action="Controller">
        <table>
            <tbody>
                <tr>
                    <td>Login: </td>
                    <td><input type="text" name="Login" value="" /></td>
                </tr>
                
                <tr>
                    <td>Senha: </td>
                    <td><input type="password" name="Senha" value="" /></td>
                </tr>
                  
            </tbody>
        </table>
        <br/>
        <input type="hidden" name="classe" value="LoginLogica" />
        <input type="hidden" name="metodo" value="entrar" />
        <input type="submit" value="Entrar" name="Entrar" />
        </form>
    </body>
</html>
