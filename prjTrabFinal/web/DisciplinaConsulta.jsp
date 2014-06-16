<%-- 
    Document   : DisciplinaConsulta
    Created on : 14/06/2014, 21:30:38
    Author     : Rafael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <script src="query/jquery-2.1.1.js"></script>      
        <script src="query/table.js"></script>
        <script>                       
            function envia(){
                                
                var campo = $('#campo').val(); 
                //window.alert(campo);
                var valor = $('#valor').val();
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
                if (confirm("Comfirma a exclusão da Disciplina: "+id+"?")) {                    
                    var valor = id;
                    var classe = "DisciplinaLogica";
                    var metod = "excluir";
                    $.get('Controller', {IdDisciplina:valor, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result').html(responseText);
                    } );
                      //window.alert("SIM");
                }
                
            }
             
window.onload=function(){tableHighlightRow();}
            
        </script>        
        
        <title>Disciplina - Consulta</title>
        <jsp:include page="verifica_login.jsp"/>
        <jsp:include page="bloqueio_secretaria.jsp"/>
        <jsp:include page="${sessionScope.menu}"/>
    </head>
    <body>
        <select id="campo">
                <option>IdDisciplina</option>
                <option>Nome</option>                
            </select>
            
            <input type="text" id="valor" />
            <input type="hidden" id="classe" value="DisciplinaLogica" />
            <input type="hidden" id="metodo" value="consultar" />            
            <input type="button" value="Consultar" name="Consultar" onclick="javascript:envia()" />
            <a href="./DisciplinaCadastro.jsp">Adicionar Disciplina</a>
                    
        <br>
         <div id="result">
         </div>
    </body>
</html>
