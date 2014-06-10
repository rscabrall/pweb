<%-- 
    Document   : AlunoConsulta
    Created on : 27/05/2014, 08:36:50
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
                if (confirm("Comfirma a exclusão do cliente " + id + " ?")) {
                    var campo = 'id'; 
                    //window.alert(campo);
                    var valor = id;
                    var classe = "AlunoLogica";
                    var metod = "excluir";
                    $.get('Controller', {campo:campo, valor:valor, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result').html(responseText);
                    } );
                      //window.alert("SIM");
                }
                
            }
             
window.onload=function(){tableHighlightRow();}
            
        </script>        
        <title>Aluno - Consultar</title>
    </head>                       
            <select id="campo">
                <option>Id</option>
                <option>Nome</option>
                <option>RG</option>
                <option>CPF</option>
            </select>
            
            <input type="text" id="valor" />
            <input type="hidden" id="classe" value="AlunoLogica" />
            <input type="hidden" id="metodo" value="consultar" />            
            <input type="button" value="Consultar" name="Consultar" onclick="javascript:envia()" />
                    
        <br>
         <div id="result">
         </div>
    </body>
</html>
