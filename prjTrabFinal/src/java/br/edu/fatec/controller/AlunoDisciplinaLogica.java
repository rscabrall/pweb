/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.controller;

import br.edu.fatec.model.AlunoDisciplina;
import br.edu.fatec.model.AlunoDisciplinaDAO;
import br.edu.fatec.model.Curso;
import br.edu.fatec.model.CursoDAO;
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
public class AlunoDisciplinaLogica extends AbstractLogica{

    @Override
    public void executa(HttpServletRequest req, HttpServletResponse resp) {
        String metodo = req.getParameter("metodo");
        ArrayList<AlunoDisciplina> list = new ArrayList<AlunoDisciplina>();
        AlunoDisciplinaDAO dao = new AlunoDisciplinaDAO();
        System.out.println("###################Logica AlunoDisc -> Comeco");
        if( metodo.equals("consultar") ){
            try {
                String campo = req.getParameter("campo");
                String valor = req.getParameter("IdDisc");
                
                list = dao.consultar(valor);
                PrintWriter html;
                int cont=0;
                String css[] = {"white", "gray"};
                
                try {                    
                    html = resp.getWriter();
                    html.println("<H1>Resultado</H1>");
                    String gambiarra;
                    if( list.isEmpty() == true){
                        html.println("Nenhum resultado encontrado.");
                    }else{
                        html.println("<table class='hilite' id='highlight'>");
                        html.println("<thead>");
                        html.println("<tr>");
                        html.println("<td>");
                        html.println("<label><b>RA &nbsp;</b></label>");
                        html.println("</td>");
                        html.println("<td>");
                        html.println("<label><b>Nome &nbsp;</b></label>");
                        html.println("</td>");                        
                        html.println("<td>");
                        html.println("<label><b>Nota &nbsp;</b></label>");
                        html.println("</td>");
                        html.println("<td>");
                        html.println("<label><b>Falta &nbsp;</b></label>");
                        html.println("</td>");
                        html.println("</tr>");
                        html.println("</thead>");
                        html.println("<tbody>");
                        for( AlunoDisciplina a:list){
                                                        
                            html.println("<tr class='"+ css[cont%2] +"'>");
                            //html.println("<tr class='hilight'>");
                            html.println("<td>");
                            html.println(a.getAluno().getRA());
                            html.println("</td>");
                            html.println("<td>");                            
                            html.println(a.getAluno().getNome());
                            html.println("</td>");                            
                            html.println("<td>");                            
                            html.println(a.getNota());
                            html.println("</td>");  
                            html.println("<td>");                            
                            html.println(a.getFaltas());
                            html.println("</td>");  
                            html.println("<td>");                            
                            html.println("<a href=AlunoDisciplinaAltera.jsp?alu=" + a.getAluno().getId() + "&disc="+a.getDisciplina().getIdDisciplina()+"&ra="+a.getAluno().getRA()+"&nome="+a.getDisciplina().getNome()+" >Alterar</a>");
                            html.println("<a href=AlunoDisciplinaAltera.jsp?obj=" + a +" >Alterar OBJ</a>");                            
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
                req.getRequestDispatcher("AlunoDisciplinaConsulta.jsp");
                  
            if( metodo.equals("verifica")){
               
            }else{                                        
                if( metodo.equals("excluir") ){
                                        
                }else{

                    System.out.println("###################Logica -> alterar");
                    int aluno = Integer.parseInt(req.getParameter("IdAluno"));            
                    String disc = req.getParameter("IdDisciplina");
                    int faltas = Integer.parseInt(req.getParameter("Faltas"));                            
                    double nota = Double.parseDouble(req.getParameter("Nota"));
                    
                    AlunoDisciplina alunoDisc = new AlunoDisciplina();
                    alunoDisc.setFaltas(faltas);
                    alunoDisc.setNota(nota);
                    System.out.println("###################Logica -> Atribui atributos - FIM");
                    if( metodo.equals("inserir") ){
                        
                    }else{                    
                            System.out.println("###################Logica -> Alterar");                                                
                            try {                                                        
                                dao.alterar(alunoDisc, aluno, disc);                        
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
