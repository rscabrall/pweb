<%-- 
    Document   : CadastroPerfil
    Created on : 15/05/2014, 10:18:14
    Author     : AD122176
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil - Cadastro</title>
    </head>
    <body>
        <h1>Perfil - Cadastro</h1>
        
         <form method="POST" action="Controller">
         <table >
           
            <tbody>
                <tr>
                    <td>Id Perfil: </td>
                    <td><input type="text" name="IdPerfil" value="" /></td>
                </tr>
                
                <tr>
                    <td>Descricao: </td>
                    <td><input type="text" name="Descricao" value="" /></td>
                </tr>
                  
            </tbody>
        </table>
<br/>
        <input type="submit" value="Cadastrar" name="Cadastrar" />
         </form>
    </body>
</html>
