<%-- 
    Document   : verifica_login
    Created on : 15/06/2014, 23:16:56
    Author     : Rafael
--%>
<%@page import="javax.swing.JOptionPane"%>
<%
    if( session.getAttribute("login") == null ){
        JOptionPane.showMessageDialog(null, "Você não tem permissão de acessar essa página.", "Aviso", JOptionPane.WARNING_MESSAGE);
        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
        rd.forward(request, response);
    }

%>
