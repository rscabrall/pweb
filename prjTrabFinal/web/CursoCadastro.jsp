<%-- 
    Document   : CursoCadastro
    Created on : 15/05/2014, 10:08:55
    Author     : AD122176
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Curso - Cadastro</title>
    </head>
    <body>
        <h1>Curso - Cadastro</h1>
         <form method="POST" action="Controller">
        <table >
           
            <tbody>
                <tr>
                    <td>Id Curso: </td>
                    <td><input type="text" name="IdDisciplina" value="" /></td>
                </tr>
                
                <tr>
                    <td>Nome: </td>
                    <td><input type="text" name="Nome" value="" /></td>
                </tr>
                <tr>
                    <td>Duracao: </td>
                    <td><input type="text" name="Duracao" value="" /></td>
                </tr>
                <tr>
                    <td>CPF: </td>
                    <td><input type="text" name="CPF" value="" /></td>
                </tr>
                <tr>
                    <td>Modalidade: </td>
                    <td><input type="text" name="Modalidade" value="" /></td>
                </tr>
                <tr>
                    <td>Disciplina: </td>
                    <td><input type="text" name="Disciplina" value="" /></td>
                </tr>
                
            </tbody>
        </table>
<br/>
        <input type="submit" value="Cadastrar" name="Cadastrar" />
         </form>
    </body>
</html>
