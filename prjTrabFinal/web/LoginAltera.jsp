<%-- 
    Document   : LoginAltera
    Created on : 15/06/2014, 01:57:31
    Author     : Rafael
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<sql:query var="rs" dataSource="jdbc/RAM_Con">
    SELECT * FROM tbLogin WHERE IdLogin=?;
    <sql:param value="${param.id}"/>
</sql:query>
<sql:query var="rsPerfil" dataSource="jdbc/RAM_Con">
    SELECT IdPerfil, Descricao FROM tbPerfil ORDER BY Descricao;
</sql:query>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="query/mascaras.js"></script>
        <title>Login - Alterar</title>
        <jsp:include page="verifica_login.jsp"/>
        <jsp:include page="bloqueio_adm.jsp"/>
        <jsp:include page="${sessionScope.menu}"/> 
    </head>
    <body>
        <h1>Login - Alterar</h1>
        <c:choose>
            <c:when test='${rs.rowCount>0}'>
                <c:forEach var="l" items="${rs.rows}">
                    <form name="form1" method="post" action="Controller">
                        <input type="hidden" readonly="" name="IdLogin" value="${l.IdLogin}" />
                        <table >                            
                            <tbody>
                                <tr>
                                    <td>Login: </td>
                                    <td><input type="text" name="Login" value="${l.Login}" /></td>
                                </tr>                                
                                <tr>
                                    <td>Senha: </td>
                                    <td><input type="password" name="Senha" value=""  maxlength="10" /></td> 
                                    <td>Máximo 10 caracteres</td> 
                                </tr>
                                <tr>
                                    <td>Confirmar senha: </td>
                                    <td><input type="password" name="Senha2" value="" onblur="validarSenha(form1.Senha, form1.Senha2, form1.Alterar );" /></td>
                                </tr>
                                <tr>
                                 <td>Perfil: </td>
                                 <td><select id="perfil" name="perfil">                            
                                             <option id="-1">[Selecione]</option>    
                                             <c:forEach var="p" items="${rsPerfil.rows}">                                                    
                                                 <c:choose>                                                        
                                                     <c:when test='${l.IdPerfil eq p.IdPerfil}'>
                                                         <option selected value="${p.IdPerfil}">${p.Descricao}</option>

                                                     </c:when>
                                                         <c:otherwise><option value="${p.IdPerfil}">${p.Descricao}</option></c:otherwise>
                                                 </c:choose>    

                                             </c:forEach>

                                     </select>
                                 </td>
                             </tr>
                            </tbody>
                        </table>
                        <br/>
                        <input type="hidden" name="classe" value="LoginLogica" />
                        <input type="hidden" name="metodo" value="alterar" />
                        <input type="submit" value="Alterar" name="Alterar" />
                    </form >
                    
                </c:forEach>
            </c:when>
                    <c:otherwise>Usu&aacuterio n&atilde; encontrado</c:otherwise>
        </c:choose>
    </body>
</html>
