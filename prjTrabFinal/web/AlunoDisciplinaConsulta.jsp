<%-- 
    Document   : AlunoDisciplinaConsulta
    Created on : 15/06/2014, 19:20:04
    Author     : Rafael
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<sql:query var="rsCurso" dataSource="jdbc/RAM_Con">
    SELECT IdCurso, Nome FROM tbCurso ORDER BY Nome;
</sql:query>
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <script src="query/jquery-2.1.1.js"></script>      
        <script src="query/table.js"></script>        
        <script>                       
            function envia(){
                                
                var campo = "IdDisciplina"; 
                //window.alert(campo);
                var valor = $("#curso option:selected").val();                
                var classe = "CursoDisciplinaLogica";
                var metod = "consultar"; 
                //var teste = "Rafael";
                //window.alert(campo);
                $.get('Controller', {campo:campo, valor:valor, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result').html(responseText);
                    } );
                    
                //window.alert(campo);    
            }                                
            
            function envia2(){
                                
                var valor1 = $("#curso option:selected").text(); 
                //window.alert(campo);
                var valor2 = $("#disciplina option:selected").val();
                //window.alert(valor2);
                var classe = $('#classe').val();
                var metod = $('#metodo').val(); 
                //var teste = "Rafael";
                //window.alert(campo);
                $.get('Controller', {IdCurso:valor1, IdDisc:valor2, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result2').html(responseText);
                    } );
                    
                //window.alert(campo);    
            }
            
        </script>
        <title>Controle de Notas e Faltas</title>
        <jsp:include page="verifica_login.jsp"/>
        <jsp:include page="bloqueio_secretaria.jsp"/>
        <jsp:include page="${sessionScope.menu}"/>
    </head>
    <body>
        <h1>Controle de Notas e Faltas</h1>
        <label>Curso: </label>
        <select id="curso" name="curso" onClick="envia()">                            
            <option id="-1">[Selecione]</option>    
            <c:forEach var="c" items="${rsCurso.rows}">
                <option value="${c.IdCurso}">${c.Nome}</option>                                                                        
            </c:forEach>

        </select>
                                        
        &nbsp;&nbsp;
         <div id="result">
         </div>
        <!--<input type="text" id="valor" />-->
        <input type="hidden" id="classe" value="AlunoDisciplinaLogica" />
        <input type="hidden" id="metodo" value="consultar" />            
        <input type="button" value="Consultar" name="Consultar" onclick="javascript:envia2()" />
        
        <div id="result2">
         </div>
        
    </body>
</html>
