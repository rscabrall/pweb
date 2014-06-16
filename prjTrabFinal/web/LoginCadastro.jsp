<%-- 
    Document   : LoginCadastro
    Created on : 15/05/2014, 10:16:37
    Author     : AD122176
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<sql:query var="rs" dataSource="jdbc/RAM_Con">
    SELECT IdPerfil, Descricao FROM tbPerfil ORDER BY Descricao;
</sql:query>
    
<sql:query var="rsCurso" dataSource="jdbc/RAM_Con">
    SELECT IdCurso, Nome FROM tbCurso ORDER BY Nome;
</sql:query>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="query/jquery-2.1.1.js"></script>              
        <script src="query/mascaras.js"></script>
        <jsp:include page="verifica_login.jsp"/>
        <jsp:include page="bloqueio_adm.jsp"/>
        <jsp:include page="${sessionScope.menu}"/>
        <script>                       
            
            function ena(){
                if( $('#result1').is(':empty') && $('#result2').is(':empty')  ){
                        return true;
                }else{
                        //window.alert("Errado");
                        return false;
                }
            }            
            function verifica(){
                x=document.getElementById("perfil1").selectedIndex;
                y=document.getElementById("perfil1").options;
                
                idp = $("#perfil1 option:selected").val();
                $("#perfil").val(idp);
                
                if( y[x].text == "Aluno"){
                    $('#aluno').css("visibility", "visible"); 
                    //$('#teste').show();
                    $("#aluno").show();
                    $("#Login").val("");
                    $("#Login").hide();
                    $("#Login").css("visibility", "hide");                     
                    $("#lblLogin").hide();
                    $("#lblLogin").css("visibility", "hide"); 
                    $("#result1").hide();
                    $("#result1").css("visibility", "hide"); 
                    $("#result1").html(""); 
                    $("#Senha").val("A12345678a");
                    $("#Senha2").val("A12345678a");
                    //window.alert("Aluno");
                }else{
                    $('#aluno').css("visibility", "hide"); 
                    $('#aluno').hide();
                    $("#Login").show();
                    $("#Login").css("visibility", "visible"); 
                    $("#lblLogin").show();
                    $("#lblLogin").css("visibility", "visible"); 
                    $("#result1").show();
                    $("#result1").css("visibility", "visible"); 
                    //$('#teste').hide();
                    $("#RA").val("");
                    $('#result2').html("");
                    //$("#Login").prop('readonly', true);                    
                    //window.alert("Nao Aluno");
                }
            }
                        
            function erro(){
                 return "Erro";
            }
            
                        
            function compara1(){
                                
                var valor = $("#Login").val();
                var classe = "LoginLogica";
                var metod = "verifica"; 
                //var teste = "Rafael";
                //window.alert(campo);
                $.get('Controller', {Login:valor, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result1').html(responseText);                        
                        
                    } );
                                 
                    
                //window.alert(campo);    
            }
            function compara2(){
                                
                var valor1 = $("#Login").val();
                var valor2 = $("#RA").val();
                //window.alert(campo);                
                var classe = "LoginLogica";
                var metod = "verifica"; 
                //var teste = "Rafael";
                //window.alert(campo);
                $.get('Controller', {Login:valor1, RA:valor2, classe:classe , metodo:metod},
                    function(responseText){
                        $('#result2').html(responseText);                        
                        
                    } );
                                
                    
                //window.alert(campo);    
            }
                        
            
        </script>        
        <title>Login - Cadastro</title>
    </head>
    <body>
        <h1>Login - Cadastro</h1>
        <table>
            <tbody>
                    <tr>
                            <td>Perfil: </td>
                            <td><select id="perfil1" name="perfil1" onchange="verifica()">                            
                                                    <option id="-1">[Selecione]</option>    
                                                    <c:forEach var="f" items="${rs.rows}">
                                                            <option value="${f.IdPerfil}">${f.Descricao}</option>
                                                    </c:forEach>
                                    </select>
                            </td>
                    </tr>
            </tbody
        </table>  
        <form id="form1" method="POST" action="Controller" onsubmit="return ena();" >
    
         <table >
           
            <tbody>                                
                <tr>
                    <td id="lblLogin">Login: </td>
                    <td><input type="text" name="Login" id="Login" value="" onblur="enable()" onkeyup="compara1()" /></td>
                    <td><div id="result1"></div></td>
                </tr>
                <tr>
                    <td>Senha: </td>
                    <td><input type="password" name="Senha"  value=""  maxlength="10" /></td>
                    <td>Máximo 10 caracteres</td> 
                </tr>
                <tr>
                    <td>Confirmar senha: </td>
                    <td><input type="password" name="Senha2" value="" onblur="validarSenha(form1.Senha2, form1.Senha, form1.Cadastrar );"  maxlength="10" /></td> 
                    
                </tr>
                
            </tbody>
        </table>
        <div id="aluno" style="visibility:hidden">                            
                     <table >
                        <tbody>                                
                            <tr>
                                <td>RA - Login: </td>
                                <td><input type="text" name="RA" id="RA" value="" onkeyup="compara2()" /></td>
                                <td><div id="result2"></div></td>
                            </tr>                            
                            <tr>
                                <td>Curso: </td>
                                <td><select id="curso" name="curso">                            
                                        <option id="-1">[Selecione]</option>    
                                        <c:forEach var="f" items="${rsCurso.rows}">
                                            <option value="${f.IdCurso}">${f.Nome}</option>                                                                        
                                        </c:forEach>

                                    </select>
                                </td>
                            </tr>

                        </tbody>
                    </table>
					
                    <br/>                    
            </div>    
            
        <input type="hidden" name="metodo" value="inserir" />
        <input type="hidden" name="perfil" id="perfil" value="" />
        <input type="hidden" name="classe" value="LoginLogica" />
        <input type="submit" value="Cadastrar" id="Cadastrar" name="Cadastrar" />
                
       </form>
       
        </body>
    
</html>
