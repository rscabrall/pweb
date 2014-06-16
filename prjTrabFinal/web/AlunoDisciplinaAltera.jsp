<%-- 
    Document   : AlunoDisciplinaAltera
    Created on : 15/06/2014, 20:54:31
    Author     : Rafael
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<sql:query var="rs" dataSource="jdbc/RAM_Con">
    SELECT * FROM tbAlunoDisciplina WHERE IdAluno=? AND IdDisciplina=?;
    <c:set var="idDisc" value="null"/>
    <sql:param value="${param.alu}"/>
    <sql:param value="${param.disc}"/>
</sql:query>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aluno Disciplina - Notas e Faltas</title>
        <jsp:include page="verifica_login.jsp"/>
        <jsp:include page="bloqueio_secretaria.jsp"/>
        <jsp:include page="${sessionScope.menu}"/>
    </head>
    <body>
        <h1>Aluno Disciplina - Notas e Faltas</h1>
        <c:choose>
            <c:when test='${rs.rowCount>0}'>
                <c:forEach var="a" items="${rs.rows}">
                    <form name="form1" method="post" action="Controller">
                        <table >                            
                            <tbody>
                                <tr>
                                    <td>RA: </td>
                                    <td>${param.ra}</td>
                                </tr>
                                <tr>
                                    <td>Disciplina: </td>
                                    <td>${param.nome}</td>
                                </tr> 
                                
                                <tr>
                                    <td>Faltas: </td>
                                    <td><input type="text" name="Faltas" value="${a.Faltas}" /></td>
                                </tr>
                                <tr>
                                    <td>Nota: </td>
                                    <td><input type="text" name="Nota" value="${a.Nota}" /></td>
                                </tr>
                                
                            </tbody>
                        </table>
                        <br/>
                        <input type="hidden" name="IdAluno" value="${param.alu}" />
                        <input type="hidden" name="IdDisciplina" value="${param.disc}" />
                        <input type="hidden" name="classe" value="AlunoDisciplinaLogica" />
                        <input type="hidden" name="metodo" value="alterar" />
                        <input type="submit" value="Alterar" name="Alterar" />
                    </form >
                    
                </c:forEach>
            </c:when>
                    <c:otherwise>Aluno n&atilde; encontrado</c:otherwise>
        </c:choose>
    </body>
</html>
