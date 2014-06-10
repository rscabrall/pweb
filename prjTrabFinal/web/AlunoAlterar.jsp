<%-- 
    Document   : AlunoAlterar
    Created on : 09/06/2014, 11:32:29
    Author     : Rafael
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<sql:query var="rs" dataSource="jdbc/RAM_Con">
    SELECT IdCurso, Nome FROM tbCurso ORDER BY Nome;
</sql:query>
<sql:query var="rsAluno" dataSource="jdbc/RAM_Con">
    SELECT * FROM tbAluno WHERE IdAluno=? ORDER BY Nome;
    <sql:param value="${param.id}"/>
</sql:query>
  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aluno - Cadastro</title>
    </head>
    <body>        
        
        <h1>Aluno - Cadastro </h1>
        <c:choose>
            <c:when test='${rsAluno.rowCount>0}'>
                <c:forEach var="a" items="${rsAluno.rows}">
                    <form method="post" action="Controller">
                        <table >                            
                            <tbody>
                                <tr>
                                    <td>Id Aluno: </td>
                                    <td><input type="text" readonly="" name="Id" value="${a.IdAluno}" /></td>
                                </tr>
                                 
                                <tr>
                                    <td>Nome: </td>
                                    <td><input type="text" name="Nome" value="${a.Nome}" /></td>
                                </tr>
                                <tr>
                                    <td>RG: </td>
                                    <td><input type="text" name="RG" value="${a.RG}" /></td>
                                </tr>
                                <tr>
                                    <td>CPF: </td>
                                    <td><input type="text" name="CPF" value="${a.CPF}" /></td>
                                </tr>
                                <tr>
                                    <td>RA: </td>
                                    <td><input type="text" name="RA" value="${a.RA}" /></td>
                                </tr>
                                <tr>
                                    <td>Endereco: </td>
                                    <td><input type="text" name="Endereco" value="${a.Endereco}" /></td>
                                </tr>
                                <tr>
                                    <td>Data Nasc: </td>
                                    <td><input type="text" name="DtNasc" value="${a.DtNasc}" /></td>
                                </tr>
                                   <tr>
                                    <td>Curso: </td>
                                    <td><select id="curso" name="curso">                            
                                                <option id="-1">[Selecione]</option>    
                                                <c:forEach var="c" items="${rs.rows}">                                                    
                                                    <c:choose>                                                        
                                                        <c:when test='${a.IdCurso eq c.IdCurso}'>
                                                            <option selected value="${c.IdCurso}">${c.Nome}</option>
                                                            
                                                        </c:when>
                                                            <c:otherwise><option value="${c.IdCurso}">${c.Nome}</option></c:otherwise>
                                                    </c:choose>    
                                                                                                                            
                                                </c:forEach>

                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <br/>
                        <input type="hidden" name="classe" value="AlunoLogica" />
                        <input type="hidden" name="metodo" value="alterar" />
                        <input type="submit" value="Alterar" name="Alterar" />
                    </form >
                    
                </c:forEach>
            </c:when>
                    <c:otherwise>Aluno n&atilde; encontrado</c:otherwise>
        </c:choose>
        
        
        </br>
        <a href="AlunoConsulta.jsp">Consultar Aluno</a>
    </body>
</html>

