<%-- 
    Document   : AlunoCadastro
    Created on : 15/05/2014, 09:53:06
    Author     : AD122176
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<sql:query var="rs" dataSource="jdbc/RAM_Con">
    SELECT IdCurso, Nome FROM tbCurso ORDER BY Nome;
</sql:query>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aluno - Cadastro</title>
    </head>
    <body>
        <h1>Aluno - Cadastro </h1>
        
        <form method="POST" action="Controller">
            
        <table >
           
            <tbody>
                <tr>
                    <td>Id Aluno: </td>
                    <td><input type="text" name="Id" value="" /></td>
                </tr>
                 <tr>
                    <td>Id Curso: </td>
                    <td><input type="text" name="IdCurso" value="" /></td>
                </tr>
                 
                <tr>
                    <td>Nome: </td>
                    <td><input type="text" name="Nome" value="" /></td>
                </tr>
                <tr>
                    <td>RG: </td>
                    <td><input type="text" name="RG" value="" /></td>
                </tr>
                <tr>
                    <td>CPF: </td>
                    <td><input type="text" name="CPF" value="" /></td>
                </tr>
                <tr>
                    <td>RA: </td>
                    <td><input type="text" name="RA" value="" /></td>
                </tr>
                <tr>
                    <td>Endereco: </td>
                    <td><input type="text" name="Endereco" value="" /></td>
                </tr>
                <tr>
                    <td>Data Nasc: </td>
                    <td><input type="text" name="DtNasc" value="" /></td>
                </tr>
                   <tr>
                    <td>Curso: </td>
                    <td><select id="curso" name="curso">                            
                                <option id="-1">[Selecione]</option>    
                                <c:forEach var="f" items="${rs.rows}">
                                    <option id="${f.IdCidade}">${f.Nome}</option>                                                                        
                                </c:forEach>
                            
                        </select>
                    </td>
                </tr>
            </tbody>
        </table>
        <br/>
        <input type="hidden" name="classe" value="AlunoLogica" />
        <input type="hidden" name="metodo" value="incluir" />
        <input type="submit" value="Cadastrar" name="Cadastrar" />
        </form >
        
        </br>
        <a href="AlunoConsulta.jsp">Consultar Aluno</a>
    </body>
</html>
