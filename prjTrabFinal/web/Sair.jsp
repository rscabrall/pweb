<%
    session.removeAttribute("login");
    session.removeAttribute("perfil");
    RequestDispatcher rd= request.getRequestDispatcher("Login.jsp");
    rd.forward(request, response);            
%>
