/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.controller;

import br.edu.fatec.model.AlunoDAO;
import br.edu.fatec.model.Curso;
import br.edu.fatec.model.CursoDAO;
import br.edu.fatec.model.Login;
import br.edu.fatec.model.LoginDAO;
import br.edu.fatec.model.PerfilDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class CursoLogica extends AbstractLogica {

    @Override
    public void executa(HttpServletRequest req, HttpServletResponse resp) {
        String metodo = req.getParameter("metodo");
        ArrayList<Curso> list = new ArrayList<Curso>();
        CursoDAO dao = new CursoDAO();                        
        System.out.println("###################Logica -> Comeco");
        if( metodo.equals("consultar") ){
            try {
                String campo = req.getParameter("campo");
                String valor = req.getParameter("valor");
                
                list = dao.consultar(campo, valor);
                PrintWriter html;
                int cont=0;
                String css[] = {"white", "gray"};
                
                try {                    
                    html = resp.getWriter();
                    html.println("<H1>Resultado</H1>");
                    
                    if( list.isEmpty() == true){
                        html.println("Nenhum resultado encontrado.");
                    }else{
                        html.println("<table class='hilite' id='highlight'>");
                        html.println("<thead>");
                        html.println("<tr>");
                        html.println("<td>");
                        html.println("<label><b>IdCurso &nbsp;</b></label>");
                        html.println("</td>");
                        html.println("<td>");
                        html.println("<label><b>Nome &nbsp;</b></label>");
                        html.println("</td>");                        
                        html.println("<td>");
                        html.println("<label><b>Modalidade &nbsp;</b></label>");
                        html.println("</td>");
                        html.println("<td>");
                        html.println("<label><b>Duracao &nbsp;</b></label>");
                        html.println("</td>");
                        html.println("</tr>");
                        html.println("</thead>");
                        html.println("<tbody>");
                        for( Curso c:list){
                                                        
                            html.println("<tr class='"+ css[cont%2] +"'>");
                            //html.println("<tr class='hilight'>");
                            html.println("<td>");
                            html.println(c.getIdCurso());
                            html.println("</td>");
                            html.println("<td>");                            
                            html.println(c.getNome());
                            html.println("</td>");                            
                            html.println("<td>");                            
                            html.println(c.getModalidade());
                            html.println("</td>");  
                            html.println("<td>");                            
                            html.println(c.getDuracao());
                            html.println("</td>");  
                            html.println("<td>");
                            html.println("<a href=CursoAltera.jsp?id=" + c.getIdCurso() + " >Alterar</a>");
                            html.println("<a href=CursoDisciplina.jsp?id=" + c.getIdCurso() + " >Disciplinas</a>");
                            html.println("<a href=# onClick='confirma(" + c.getIdCurso()+ ")' >Excluir</a>");
                            html.println("</td>");
                            html.println("</tr>");
                            cont++;
                            
                        }
                        html.println("</tbody>");
                        html.println("</table>");
                        html.close();
                    }
                    
                    //fechar conexão
                    //dao.getLink().fecharConexao();
                    //System.out.println("###################DAO -> fechou");
                    
                } catch (IOException ex) {
                    Logger.getLogger(AlunoLogica.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(AlunoLogica.class.getName()).log(Level.SEVERE, null, ex);
            }            
                                                                       
        }else{
            
           
            RequestDispatcher rd =
                req.getRequestDispatcher("CursoConsulta.jsp");
                  
            if( metodo.equals("verifica")){
               int id;
                System.out.println("###################Logica -> Verifica: " + req.getParameter("IdCurso") );
                id = Integer.parseInt(req.getParameter("IdCurso"));
                
                if( dao.verifica(id)==true ){
                    System.out.println("###################Curso -> Já existe");
                   try {
                       PrintWriter html;
                       html = resp.getWriter();
                       html.println("Curso j&aacute existe!");
                   } catch (IOException ex) {
                       Logger.getLogger(LoginLogica.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }

            }else{                                        
                if( metodo.equals("excluir") ){
                    System.out.println("###################Logica -> Excluir");
                    
                    int id = Integer.parseInt(req.getParameter("valor"));
                    try {                    
                        dao.excluir(id);                        
                        //JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso.", "Excluir", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        Logger.getLogger(AlunoLogica.class.getName()).log(Level.SEVERE, null, ex);
                        //JOptionPane.showMessageDialog(null, "Erro: " + ex, "Excluir", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }else{

                    System.out.println("###################Logica -> Incluir or alterar");
                    String nome = req.getParameter("Nome");            
                    String modalidade = req.getParameter("Modalidade");
                    String duracao = req.getParameter("Duracao");                            
                    
                    int id = Integer.parseInt(req.getParameter("IdCurso"));
                    Curso curs = new Curso();                
                    curs.setIdCurso(id);
                    curs.setNome(nome);
                    curs.setModalidade(modalidade);
                    curs.setDuracao(duracao);

                    System.out.println("###################Logica -> Atribui atributos - FIM");
                    if( metodo.equals("inserir") ){
                        System.out.println("###################Logica -> Inserir");
                         rd = req.getRequestDispatcher("CursoDisciplina.jsp");
                         req.setAttribute("id", curs.getIdCurso());
                         
                        try {                    
                            dao.inserir(curs);                            
                            rd.forward(req, resp);
                            //JOptionPane.showMessageDialog(null, "Inserção realizada com sucesso.", "Inserir", JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            Logger.getLogger(AlunoLogica.class.getName()).log(Level.SEVERE, null, ex);                    
                            JOptionPane.showMessageDialog(null, "Erro: " + ex, "Inserir", JOptionPane.ERROR_MESSAGE);
                        } catch (ServletException ex) {
                            Logger.getLogger(AlunoLogica.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(AlunoLogica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{                    
                            System.out.println("###################Logica -> Alterar");                                                
                            try {                                                        
                                dao.alterar(curs);                        
                                rd.forward(req, resp);
                            } catch (SQLException ex) {
                                Logger.getLogger(AlunoLogica.class.getName()).log(Level.SEVERE, null, ex);                        
                            } catch (ServletException ex) {
                                Logger.getLogger(AlunoLogica.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(AlunoLogica.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }                
            }
            
        }
    }
    
}
