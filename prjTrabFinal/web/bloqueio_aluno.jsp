<%@page import="javax.swing.JOptionPane"%>
<%
    int perfil = (Integer)session.getAttribute("perfil");
    if( perfil != 1 && perfil != 2  ){
        //JOptionPane.showMessageDialog(null, "Você não tem permissão de acessar essa página.", "Aviso", JOptionPane.WARNING_MESSAGE);
        String pagina = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1, request.getRequestURL().length());
        
        request.setAttribute("erro", "Você não tem permissão para acessar a página: " + pagina +".");
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

%>