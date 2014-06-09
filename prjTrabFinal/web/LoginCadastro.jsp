<%-- 
    Document   : LoginCadastro
    Created on : 15/05/2014, 10:16:37
    Author     : AD122176
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Cadastro</title>
    </head>
    <body>
        <h1>Login - Cadastro</h1>
        <form method="POST" action="Controller">
    
         <table >
           
            <tbody>
                <tr>
                    <td>Id Login: </td>
                    <td><input type="text" name="IdLogin" value="" /></td>
                </tr>
                
                <tr>
                    <td>Login: </td>
                    <td><input type="text" name="Login" value="" /></td>
                </tr>
                <tr>
                    <td>Senha: </td>
                    <td><input type="text" name="Senha" value="" /></td>
                </tr>
       
                
            </tbody>
        </table>
            
<br/>
        <input type="submit" value="Cadastrar" name="Cadastrar" />
       </form>
        </body>
    
</html>
