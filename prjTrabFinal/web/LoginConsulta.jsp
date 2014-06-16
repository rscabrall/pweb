<%-- 
    Document   : LoginConsulta
    Created on : 15/06/2014, 01:57:48
    Author     : Rafael
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<sql:query var="rs" dataSource="jdbc/RAM_Con">
    SELECT IdPerfil, Descricao FROM tbPerfil ORDER BY Descricao;
</sql:query>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <script src="query/jquery-2.1.1.js"></script>      
        <script src="query/table.js"></script>
        <jsp:include page="verifica_login.jsp"/>
        <jsp:include page="bloqueio_adm.jsp"/>
        <jsp:include page="${sessionScope.menu}"/> 
        <script>                       
            function envia(){
                                
                var campo = $('#campo').val(); 
                //window.alert(campo);
                 x=document.getElementById("campo").selectedIndex;
                y=document.getElementById("campo").options;
                if( y[x].text == "Perfil"){
                    var valor = $('#valor2').val();
                }else{
                    var valor = $('#valor').val();                    
                }
                var classe = $('#classe').val();
                var metod = $('#metodo').val(); 
                //var teste = "Rafael";
                //window.alert(campo);
                $.get('Controller', {campo:campo, valor:valor, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result').html(responseText);
                    } );
                    
                //window.alert(campo);    
            }
            
            function confirma(id){
                if (confirm("Comfirma a exclusão do Login: "+id+"?")) {                    
                    var valor = id;
                    var classe = "LoginLogica";
                    var metod = "excluir";
                    $.get('Controller', {IdDisciplina:valor, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result').html(responseText);
                    } );
                      //window.alert("SIM");
                }
                
            }
            function muda(){
                x=document.getElementById("campo").selectedIndex;
                y=document.getElementById("campo").options;
                if( y[x].text == "Perfil"){
                    $('#valor2').css("visibility", "visible"); 
                    //$('#teste').show();
                    $("#valor2").show();
                    $("#valor").hide();
                    $("#valor").css("visibility", "hide");                     
                    //window.alert("Aluno");
                }else{
                    $('#valor2').css("visibility", "hide"); 
                    $('#valor2').hide();
                    $("#valor").show();
                    $("#valor").css("visibility", "visible");                                         
                }
            }
             
window.onload=function(){tableHighlightRow();}
            
        </script>        
        
        <title>Login - Consulta</title>
        
    </head>
    <body>
        <select id="campo" onchange="muda()">
                <option>Login</option>
                <option>Perfil</option><!--Fazer abrir outra combo-->                
        </select>
        
        <input type="text" id="valor" />
        <select id="valor2" name="valor2" style="visibility:hidden">                            
            <option id="-1">[Selecione]</option>    
            <c:forEach var="f" items="${rs.rows}">
                    <option value="${f.IdPerfil}">${f.Descricao}</option>                                                                        
            </c:forEach>
        </select>
        
        <input type="hidden" id="classe" value="LoginLogica" />
        <input type="hidden" id="metodo" value="consultar" />            
        <input type="button" value="Consultar" name="Consultar" onclick="javascript:envia()" />
        <a href="./LoginCadastro.jsp">Adicionar Usu&aacute;rio</a>
        <br>
         <div id="result">
         </div>
    </body>
</html>
