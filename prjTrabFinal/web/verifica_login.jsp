<%-- 
    Document   : verifica_login
    Created on : 15/06/2014, 23:16:56
    Author     : Rafael
--%>
<%@page import="javax.swing.JOptionPane"%>
<%
    if( session.getAttribute("login") == null ){
        //JOptionPane.showMessageDialog(null, "Voc� n�o tem permiss�o de acessar essa p�gina.", "Aviso", JOptionPane.WARNING_MESSAGE);        
        String pagina = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1, request.getRequestURL().length());
        
        request.setAttribute("erroAccess", "Voc� n�o tem permiss�o para acessar a p�gina: " + pagina +".");
        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
        rd.forward(request, response);
    }

%>
