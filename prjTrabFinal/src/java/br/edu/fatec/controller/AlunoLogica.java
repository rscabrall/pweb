/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.controller;

import br.edu.fatec.model.Aluno;
import br.edu.fatec.model.AlunoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class AlunoLogica extends AbstractLogica{

    @Override
    public void executa(HttpServletRequest req, HttpServletResponse resp) {
        String metodo = req.getParameter("metodo");
        ArrayList<Aluno> list = new ArrayList<Aluno>();
        AlunoDAO dao = new AlunoDAO();
        
        if( metodo.equals("consultar") ){
            String campo = req.getParameter("campo");
            String valor = req.getParameter("valor");
            list = dao.consultar(campo, valor);            
            PrintWriter html;            
            
            try {
                html = resp.getWriter();
                html.println("<H1>Resultado</H1>");
                
                if( list.isEmpty() == true){
                    html.println("Nenhum resultado encontrado.");
                }else{
                    html.println("<table class='hilite' id='highlight' >");
                    html.println("<thead>");
                    html.println("<tr>");
                    html.println("<td>");
                    html.println("<label><b>Id &nbsp;</b></label>");
                    html.println("</td>");
                    html.println("<td>");
                    html.println("<label><b>Nome &nbsp;</b></label>"); 
                    html.println("</td>");
                    html.println("<td>");                 
                    html.println("<label><b>RA &nbsp;</b></label>");
                    html.println("</td>");
                    html.println("<td>");                 
                    html.println("<label><b>CPF &nbsp;</b></label>");
                    html.println("</td>");
                    html.println("<td>");                 
                    html.println("<label><b>RG &nbsp;</b></label>");
                    html.println("</td>");                                       
                    html.println("</tr>");
                    html.println("</thead>");
                    html.println("<tbody>");
                    for( Aluno a:list){
                    
                        html.println("<tr>");
                        html.println("<td>");
                        html.println(a.getId());
                        html.println("</td>");
                        html.println("<td>");
                        html.println(a.getNome());
                        html.println("</td>");
                        html.println("<td>");
                        html.println(a.getRA());
                        html.println("</td>");
                        html.println("<td>");
                        html.println(a.getCPF());
                        html.println("</td>");
                        html.println("<td>");
                        html.println(a.getRG());
                        html.println("</td>");
                        html.println("<td>");
                        html.println("<a href=AlunoAlterar.jsp?id=" + a.getId() + " >Alterar</a>");
                        html.println("<a href=AlunoExcluir.jsp?id=" + a.getId()+ " >Excluir</a>");
                        html.println("</td>");                    
                     
                }
                    html.println("</tbody>");
                     html.println("</table>");
             html.close();             
            }
            
            //fechar conexão            
            dao.getLink().fecharConexao();
            System.out.println("###################DAO -> fechou");
                    
                } catch (IOException ex) {
                Logger.getLogger(AlunoLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                                                                       
        }
        
    }
    
}
