<%@page import="javax.swing.JOptionPane"%>
<%
    int perfil = (Integer)session.getAttribute("perfil");
    if( perfil != 1 && perfil != 2  ){
        //JOptionPane.showMessageDialog(null, "Voc� n�o tem permiss�o de acessar essa p�gina.", "Aviso", JOptionPane.WARNING_MESSAGE);
        String pagina = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1, request.getRequestURL().length());
        
        request.setAttribute("erro", "Voc� n�o tem permiss�o para acessar a p�gina: " + pagina +".");
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

%>