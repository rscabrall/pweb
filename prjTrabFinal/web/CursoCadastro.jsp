<%-- 
    Document   : CursoCadastro
    Created on : 15/05/2014, 10:08:55
    Author     : AD122176
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="query/jquery-2.1.1.js"></script> 
        <jsp:include page="verifica_login.jsp"/>
        <jsp:include page="bloqueio_secretaria.jsp"/>
        <jsp:include page="${sessionScope.menu}"/>
        <title>Curso - Cadastro</title>
        <script>
            
            function ena(){
                if( $('#result').is(':empty') ){
                        return true;
                }else{
                        //window.alert("Errado");
                        return false;
                }
            }     
            function compara(){
                                
                var valor = $("#IdCurso").val();
                var classe = "CursoLogica";
                var metod = "verifica"; 
                //var teste = "Rafael";
                //window.alert(campo);
                $.get('Controller', {IdCurso:valor, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result').html(responseText);                        
                        
                    } );
                                 
                    
                //window.alert(campo);    
            }
            
        </script>
    </head>
    <body>
        <h1>Curso - Cadastro</h1>
        <form method="POST" action="Controller" onsubmit="return ena()">
        <table >
           
            <tbody>                                
                <tr>
                    <td>IdCurso: </td>
                    <td><input type="text" name="IdCurso" id="IdCurso" value="" onkeyup="compara()" /></td>
                    <td><div id="result"></div></td>
                </tr>
                <tr>
                    <td>Nome: </td>
                    <td><input type="text" name="Nome" value="" /></td>
                </tr>
                <tr>
                    <td>Duracao: </td>
                    <td><input type="text" name="Duracao" value="" /></td>
                </tr>                
                <tr>
                    <td>Modalidade: </td>
                    <td><input type="text" name="Modalidade" value="" /></td>
                </tr>                
                
            </tbody>
        </table>
<br/>
            <input type="hidden" name="classe" value="CursoLogica" />
            <input type="hidden" name="metodo" value="inserir" />                        
            <input type="submit" value="Cadastrar" name="Cadastrar" />
         </form>
    </body>
</html>
