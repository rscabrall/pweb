<%@page import="javax.swing.JOptionPane"%>
<%
    int perfil = (Integer)session.getAttribute("perfil");
    if( perfil != 1 && perfil != 2  ){
        JOptionPane.showMessageDialog(null, "Voc� n�o tem permiss�o de acessar essa p�gina.", "Aviso", JOptionPane.WARNING_MESSAGE);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

%>