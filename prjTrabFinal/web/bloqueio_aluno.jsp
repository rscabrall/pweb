<%@page import="javax.swing.JOptionPane"%>
<%
    int perfil = (Integer)session.getAttribute("perfil");
    if( perfil != 1 && perfil != 2  ){
        JOptionPane.showMessageDialog(null, "Você não tem permissão de acessar essa página.", "Aviso", JOptionPane.WARNING_MESSAGE);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

%>