<%-- 
    Document   : Disciplina Cadastro
    Created on : 15/05/2014, 10:14:38
    Author     : AD122176
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="query/jquery-2.1.1.js"></script>      
        <script src="query/table.js"></script>
        <jsp:include page="verifica_login.jsp"/>
        <jsp:include page="bloqueio_secretaria.jsp"/>
        <jsp:include page="${sessionScope.menu}"/>
        <script>
            
            function verifica(){
                                
                var campo = "IdDisciplina"; 
                //window.alert(campo);
                var valor = $('#IdDisciplina').val();
                var classe = "DisciplinaLogica";
                var metod = "verifica"; 
                //var teste = "Rafael";
                //window.alert(campo);
                $.get('Controller', {campo:campo, valor:valor, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result').html(responseText);                        
                        
                    } );
                                
                    
                //window.alert(campo);    
            }            
            
            function enable(){
                if( $('#result').is(':empty') ){
                    document.getElementById("Cadastrar").disabled = false;
                }else{
                    //window.alert("Errado");
                    document.getElementById("Cadastrar").disabled = true;
                }
            }
                        
             
        window.onload=function(){tableHighlightRow();}
            
        </script>
        <title>Disciplina - Cadastro</title>
    </head>
    <body>
        <h1>Disciplina - Cadastro</h1>
        <form method="POST" action="Controller">
            <table >

               <tbody>
                   <tr>
                       <td>Id Disciplina: </td>
                       <!--<td><input type="text" id="IdDisciplina" name="IdDisciplina" value="" onblur="enable()" onkeyup="verifica()" /></td>-->
                       <td><input type="text" id="IdDisciplina" name="IdDisciplina" value="" onkeyup="verifica()" onblur="enable()"/></td>
                       <td><div id="result"></div></td>
                   </tr>

                   <tr>
                       <td>Nome: </td>
                       <td><input type="text" name="Nome" value="" /></td>
                   </tr>
                   <tr>
                       <td>Carga Horária: </td>
                       <td><input type="text" name="CargaHoraria" value="" /></td>
                   </tr>


               </tbody>
           </table>
            <br/>
            <input type="hidden" name="classe" value="DisciplinaLogica" />
            <input type="hidden" name="metodo" value="inserir" />
            <input type="submit" value="Cadastrar" id="Cadastrar" name="Cadastrar" />
        </form>
    </body>
</html>
