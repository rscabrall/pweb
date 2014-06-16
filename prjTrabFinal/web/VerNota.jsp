<%-- 
    Document   : VerNota
    Created on : 15/06/2014, 22:17:43
    Author     : Rafael
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<sql:query var="rs" dataSource="jdbc/RAM_Con">
    SELECT tbAlunoDisciplina.*, TbDisciplina.Nome, TbDisciplina.Carga_Horaria FROM tbAlunoDisciplina, tbDisciplina WHERE tbAlunoDisciplina.IdDisciplina=tbDisciplina.IdDisciplina AND tbAlunoDisciplina.IdAluno=?;    
    <sql:param value="${sessionScope.aluno}"/>    
</sql:query>
<html>
    <head>
        <jsp:include page="verifica_login.jsp"/>
        <jsp:include page="bloqueio_aluno.jsp"/>
        <jsp:include page="${sessionScope.menu}"/>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <script src="query/jquery-2.1.1.js"></script>      
        <script src="query/table.js"></script>        
        <title>Minhas Notas e Faltas</title>
    </head>
    <body>
        <h1>Minhas Notas e Faltas</h1>         
            <c:choose>
                <c:when test='${rs.rowCount>0}'>
                    <% 
                         int cont=0;
                         String css[] = {"white", "gray"};                      
                    %>    
                    <table class="hilite" id="highlight">
                            <thead>
                                    <tr>
                                            <td>
                                            <label><b>Disciplina &nbsp;</b></label>
                                            </td>
                                            <td>
                                            <label><b>Faltas &nbsp;</b></label>
                                            </td>                        
                                            <td>
                                            <label><b>Nota &nbsp;</b></label>
                                            </td>
                                            <td>
                                            <label><b>Carga Horaria &nbsp;</b></label>
                                            </td>
                                    </tr>
                            </thead>
                            <tbody>
                    <c:forEach var="di" items="${rs.rows}">                    


                                      <% 
                                          out.print("<tr class='"+css[cont%2]+"'>"); 
                                          cont++;
                                      %>    
                                            <td>${di.Nome}</td>                                                                                                    
                                            <td>${di.Faltas}</td>                                                                                                    
                                            <td>${di.Nota}</td>
                                            <td>${di.Carga_Horaria}</td>                                                                                        
                                    </tr>			                                                                              
                    </c:forEach>
                      </tbody>
                    </table>                
                </c:when>
                            <c:otherwise>N&amacr;o H&aacute; Disciplinas para esse Aluno</c:otherwise>
            </c:choose>
                            <a href="aluno.jsp">Menu</a>                            
    </body>
</html>
