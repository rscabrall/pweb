<%-- 
    Document   : CursoConsulta
    Created on : 15/06/2014, 16:36:26
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
        <jsp:include page="verifica_login.jsp"/>
        <jsp:include page="bloqueio_secretaria.jsp"/>
        <jsp:include page="${sessionScope.menu}"/>
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
                if (confirm("Comfirma a exclusão do Curso cujo Id: " + id + " ?")) {
                    var campo = 'Id'; 
                    //window.alert(campo);
                    var valor = id;
                    var classe = "CursoLogica";
                    var metod = "excluir";
                    $.get('Controller', {Id:campo, valor:valor, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result').html(responseText);
                    } );
                      //window.alert("SIM");
                }
                
            }             
            
        </script>
        <title>Curso - Consulta</title>
    </head>
    <body>        
        <select id="campo">
                <option>IdCurso</option>
                <option>Nome</option>
                <option>Modalidade</option>                
            </select>
            
            <input type="text" id="valor" />
            <input type="hidden" id="classe" value="CursoLogica" />
            <input type="hidden" id="metodo" value="consultar" />            
            <input type="button" value="Consultar" name="Consultar" onclick="javascript:envia()" />
            <a href="./CursoCadastro.jsp">Adicionar Curso</a>
        <br>
         <div id="result">
         </div>
    </body>
</html>
