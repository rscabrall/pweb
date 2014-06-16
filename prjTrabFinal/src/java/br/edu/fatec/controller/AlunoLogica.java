/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.controller;

import br.edu.fatec.model.Aluno;
import br.edu.fatec.model.AlunoDAO;
import br.edu.fatec.model.LoginDAO;
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
 * @author Gabriel
 */
public class AlunoLogica extends AbstractLogica{

    @Override
    public void executa(HttpServletRequest req, HttpServletResponse resp) {
        String metodo = req.getParameter("metodo");
        ArrayList<Aluno> list = new ArrayList<Aluno>();
        AlunoDAO dao = new AlunoDAO();
        LoginDAO daoLogin = new LoginDAO();
        System.out.println("###################Logica -> Comeco");
        if( metodo.equals("consultar") ){
            System.out.println("###################Logica -> Consulta");
            try {
                String campo = req.getParameter("campo");
                String valor = req.getParameter("valor");
                list = dao.consultar(campo, valor);
                System.out.println("###################Logica -> Consulta Pegou");
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
                            
                            
                            html.println("<tr class='"+ css[cont%2] +"'>");
                            //html.println("<tr class='hilight'>");
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
                            html.println("<a href=# onClick='confirma(" + a.getId()+ ")' >Excluir</a>");
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
                req.getRequestDispatcher("AlunoConsulta.jsp");
                                                    
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
                String ra = req.getParameter("RA");            
                String rg = req.getParameter("RG");            
                String cpf = req.getParameter("CPF");           
                String nome = req.getParameter("Nome");            
                String ender = req.getParameter("Endereco");            
                String data = req.getParameter("DtNasc");            
                int curso = Integer.parseInt(req.getParameter("curso"));

                Aluno alu = new Aluno(curso, nome);
                //alu.setId(id);
                alu.setRA(ra);
                alu.setRG(rg);
                alu.setCPF(cpf);
                alu.setEndereco(ender);
                alu.setDtNasc(data);
                System.out.println("###################Logica -> Atribui atributos - FIM");
                if( metodo.equals("inserir") ){
                    
                    System.out.println("###################Logica -> Inserir");
                    try {                    
                        dao.inserir(alu);
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
                    int id = Integer.parseInt(req.getParameter("Id"));
                    String senha = req.getParameter("Senha2");
                    
                    try {                        
                        alu.setId(id);
                        dao.alterar(alu);
                        if( !senha.equals("") ){
                            System.out.println("###################Logica -> Alterar Senha");
                            int idLog = Integer.parseInt(req.getParameter("IdLogin"));
                            daoLogin.alterar(daoLogin.pegaLogin(idLog));                            
                        }
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
