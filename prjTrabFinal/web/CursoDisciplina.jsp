<%-- 
    Document   : CursoDisciplina
    Created on : 15/06/2014, 17:35:02
    Author     : Rafael
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>


<sql:query var="rs" dataSource="jdbc/RAM_Con">
    SELECT * FROM tbCurso WHERE IdCurso=?;    
    <c:choose>                                                        
        <c:when test='${not empty requestScope.id}'>
            <sql:param value="${requestScope.id}"/>
        </c:when>
        <c:otherwise><sql:param value="${param.id}"/></c:otherwise>
    </c:choose>    
</sql:query>
<sql:query var="rsDisc" dataSource="jdbc/RAM_Con">
    SELECT * FROM tbDisciplina ORDER BY Nome;    
</sql:query>
<sql:query var="rsCursDisc" dataSource="jdbc/RAM_Con">
    SELECT tbCursoDisciplina.*, tbDisciplina.Nome, tbDisciplina.Carga_Horaria  FROM tbDisciplina, tbCursoDisciplina WHERE tbDisciplina.IdDisciplina=tbCursoDisciplina.IdDisciplina AND IdCurso=?;
    <c:choose>                                                        
        <c:when test='${not empty requestScope.id}'>
            <sql:param value="${requestScope.id}"/>
        </c:when>
        <c:otherwise><sql:param value="${param.id}"/></c:otherwise>
    </c:choose>    
</sql:query>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <script src="query/jquery-2.1.1.js"></script>      
        <script src="query/table.js"></script>
        <jsp:include page="verifica_login.jsp"/>        
        <jsp:include page="${sessionScope.menu}"/>
        <script>                                               
            function confirma(id){
                if (confirm("Comfirma a exclusão da Disciplina cujo Id: " + id + " ?")) {                    
                    var valor = id;
                    var valor2 = $('#IdCurso').val();
                    var classe = "CursoDisciplinaLogica";
                    var metod = "excluir";
                    $.post('Controller', {disc:valor, curso:valor2 ,classe:classe , metodo:metod} );                                        
                      //window.alert("SIM");
                }
                
            }             
            
        </script>
        <title>Curso Disciplina</title>
    </head>
    <body>
        <h1>Curso</h1>
        <c:choose>
            <c:when test='${rs.rowCount>0}'>
                <c:forEach var="a" items="${rs.rows}">                    
                        <table >                            
                            <tbody>
                                <tr>
                                    <td>Id Curso: </td>
                                    <td>${a.IdCurso}</td>
                                </tr>
                                <tr>
                                    <td>Nome: </td>
                                    <td>${a.Nome}</td>
                                </tr>                                 
                                <tr>
                                    <td>Modalidade: </td>
                                    <td>${a.Modalidade}</td>
                                </tr>
                                <tr>
                                    <td>Duracao: </td>
                                    <td>${a.Duracao}</td>
                                </tr>
                                
                            </tbody>
                        </table>
                        <br/>                                                                    
                        <br/>
                        
                        <c:if test="${sessionScope.perfil != 2}">
                                                        	
                                                        
                            <h3>Disciplinas</h3>

                            <form method="POST" action="Controller">
                                <label>Disciplina: </label>
                                    <select id="disciplina" name="disciplina">                            
                                        <option id="-1">[Selecione]</option>    
                                        <c:forEach var="d" items="${rsDisc.rows}">
                                                <option value="${d.IdDisciplina}">${d.Nome}</option>
                                        </c:forEach>
                                    </select>
                                &nbsp;
                                <label>Semestre: </label>
                                <input type="text" value="" name="Semestre" id="Semestre" />
                                <input type="hidden" name="classe" value="CursoDisciplinaLogica" />
                                <input type="hidden" name="metodo" value="inserir" />
                                <input type="hidden" name="IdCurso" id="IdCurso" value="${a.IdCurso}" />                            
                                <input type="submit" value="Adicionar" name="Adicionar" />
                             </form>
                         </c:if>   
                        <% 
                               int cont=0;
                                String css[] = {"white", "gray"};
                        %> 
                        <c:choose>
                            <c:when test='${rsCursDisc.rowCount>0}'>
                                <table class="hilite" id="highlight">
                                        <thead>
                                                <tr>
                                                        <td>
                                                        <label><b>IdDisciplina &nbsp;</b></label>
                                                        </td>
                                                        <td>
                                                        <label><b>Nome &nbsp;</b></label>
                                                        </td>                        
                                                        <td>
                                                        <label><b>Carga Horaria &nbsp;</b></label>
                                                        </td>
                                                        <td>
                                                        <label><b>Semestre &nbsp;</b></label>
                                                        </td>
                                                </tr>
                                        </thead>
                                        <tbody>
                                <c:forEach var="di" items="${rsCursDisc.rows}">                    
                                    
                                                                                                         
                                                  <% 
                                                      out.print("<tr class='"+css[cont%2]+"'>"); 
                                                      cont++;
                                                  %>    
                                                        <td>${di.IdDisciplina}</td>                                                                                                    
                                                        <td>${di.Nome}</td>                                                                                                    
                                                        <td>${di.Carga_Horaria}</td>
                                                        <td>${di.SemestreCurso}</td>
                                                        <c:if test="${sessionScope.perfil != 2}">
                                                        	<td><a href=# onClick="confirma('${di.IdDisciplina}')" >Excluir</a></td>
                                                        </c:if>
                                                                                                                    
                                                </tr>			                                                                              
                                </c:forEach>
                                  </tbody>
                                </table>                
                            </c:when>
                                        <c:otherwise>N&amacr;o H&aacute; Disciplinas para esse Curso</c:otherwise>
                        </c:choose>
                        
                </c:forEach>
            </c:when>
                    <c:otherwise>Curso n&atilde; encontrado</c:otherwise>
        </c:choose>
    </body>
</html>
