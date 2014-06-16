<%-- 
    Document   : DisciplinaAltera
    Created on : 14/06/2014, 21:56:57
    Author     : Rafael
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<sql:query var="rsDisc" dataSource="jdbc/RAM_Con">
    SELECT * FROM tbDisciplina WHERE IdDisciplina=? ;
    <sql:param value="${param.id}"/>
</sql:query>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Disciplina - Alterar</title>
        <jsp:include page="verifica_login.jsp"/>
        <jsp:include page="bloqueio_secretaria.jsp"/>
        <jsp:include page="${sessionScope.menu}"/>
    </head>
    <body>
        <h1>Disciplina - Alterar </h1>
        <c:choose>
            <c:when test='${rsDisc.rowCount>0}'>
                <c:forEach var="d" items="${rsDisc.rows}">
                    <form name="form1" method="post" action="Controller">
                        <table >                            
                            <tbody>
                                <tr>
                                    <td>IdDisciplina: </td>
                                    <td><input type="text" readonly="" name="IdDisciplina" value="${d.IdDisciplina}" /></td>
                                </tr>
                                <tr>
                                    <td>Nome: </td>
                                    <td><input type="text" name="Nome" value="${d.Nome}" /></td>
                                </tr> 
                                <tr>
                                    <td>Carca Hor&aacute;ria: </td>
                                    <td><input type="text" name="CargaHoraria" value="${d.Carga_Horaria}"  maxlength="10" /></td>                                     
                                </tr>                                                                
                            </tbody>
                        </table>
                        <br/>
                        <input type="hidden" name="classe" value="DisciplinaLogica" />
                        <input type="hidden" name="metodo" value="alterar" />
                        <input type="submit" value="Alterar" name="Alterar" />
                    </form >
                    
                </c:forEach>
            </c:when>
                    <c:otherwise>Disciplina n&atilde; encontrada</c:otherwise>
        </c:choose>
        
        
        </br>
        <a href="DisciplinaConsulta.jsp">Consultar Aluno</a>
    </body>
</html>
