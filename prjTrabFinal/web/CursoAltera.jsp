<%-- 
    Document   : CursoAltera
    Created on : 15/06/2014, 16:54:11
    Author     : Rafael
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>


<sql:query var="rs" dataSource="jdbc/RAM_Con">
    SELECT * FROM tbCurso WHERE IdCurso=?;
    <sql:param value="${param.id}"/>
</sql:query>
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Curso - Alterar</title>
        <jsp:include page="verifica_login.jsp"/>
        <jsp:include page="bloqueio_secretaria.jsp"/>
        <jsp:include page="${sessionScope.menu}"/>
    </head>
    <body>
        <h1>Aluno - Alterar </h1>
        <c:choose>
            <c:when test='${rs.rowCount>0}'>
                <c:forEach var="a" items="${rs.rows}">
                    <form name="form1" method="post" action="Controller">
                        <table >                            
                            <tbody>
                                <tr>
                                    <td>Id Curso: </td>
                                    <td><input type="text" readonly="" name="IdCurso" value="${a.IdCurso}" /></td>
                                </tr>
                                <tr>
                                    <td>Nome: </td>
                                    <td><input type="text" name="Nome" value="${a.Nome}" /></td>
                                </tr>                                 
                                <tr>
                                    <td>Modalidade: </td>
                                    <td><input type="text" name="Modalidade" value="${a.Modalidade}" /></td>
                                </tr>
                                <tr>
                                    <td>Duracao: </td>
                                    <td><input type="text" name="Duracao" value="${a.Duracao}" /></td>
                                </tr>
                                
                            </tbody>
                        </table>
                        <br/>                        
                        <input type="hidden" name="classe" value="CursoLogica" />
                        <input type="hidden" name="metodo" value="alterar" />
                        <input type="submit" value="Alterar" name="Alterar" />
                    </form >
                    
                </c:forEach>
            </c:when>
                    <c:otherwise>Curso n&atilde; encontrado</c:otherwise>
        </c:choose>
    </body>
</html>
