<%-- 
    Document   : Disciplina Cadastro
    Created on : 15/05/2014, 10:14:38
    Author     : AD122176
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Disciplina - Cadastro</title>
    </head>
    <body>
        <h1>Disciplina - Cadastro</h1>
        <form method="POST" action="Controller">
         <table >
           
            <tbody>
                <tr>
                    <td>Id Disciplina: </td>
                    <td><input type="text" name="IdDisciplina" value="" /></td>
                </tr>
                
                <tr>
                    <td>Nome: </td>
                    <td><input type="text" name="Nome" value="" /></td>
                </tr>
                <tr>
                    <td>Carga Horária: </td>
                    <td><input type="text" name="CargaHoraria" value="" /></td>
                </tr>
                
                
            </tbody>
        </table>
<br/>
        <input type="submit" value="Cadastrar" name="Cadastrar" />
        </form>
    </body>
</html>
