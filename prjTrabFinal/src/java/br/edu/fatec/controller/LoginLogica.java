/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.controller;

import br.edu.fatec.model.Aluno;
import br.edu.fatec.model.AlunoDAO;
import br.edu.fatec.model.CursoDAO;
import br.edu.fatec.model.Disciplina;
import br.edu.fatec.model.DisciplinaDAO;
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
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author ad122193
 */
public class LoginLogica extends AbstractLogica {

    @Override
    public void executa(HttpServletRequest req, HttpServletResponse resp) {
        String metodo = req.getParameter("metodo");
        ArrayList<Login> list = new ArrayList<Login>();
        LoginDAO dao = new LoginDAO();        
        PerfilDAO daoPerfil = new PerfilDAO();
        AlunoDAO daoAluno = new AlunoDAO();
        CursoDAO daoCurso = new CursoDAO();
        //ArrayList<Aluno> listAluno = new ArrayList<Aluno>();
        
        System.out.println("###################Logica -> Comeco");
        
        if(metodo.equals("entrar"))
        {    
            System.out.println("###################Logica -> Entrar");
            String login = req.getParameter("Login");            
            String senha = req.getParameter("Senha");                            
            int log = dao.validaLogin(login, senha);
            String pag;
            Login user;
            Aluno aluno;
            if( log != -1  ){
                try {
                    System.out.println("###################Logica -> Tem");
                    user=dao.pegaLogin(log);
                    HttpSession sessao = req.getSession();
                    sessao.setAttribute("login", user.getLogin());
                    sessao.setAttribute("perfil", user.getPerfil().getIdPerfil());
                    
                    if( user.getPerfil().getIdPerfil() == 1) pag="adm.jsp"; else if( user.getPerfil().getIdPerfil() == 2) pag="aluno.jsp"; else pag="secretaria.jsp";
                    
                    if(user.getPerfil().getIdPerfil() == 2){
                        aluno = daoAluno.pegaID(user.getIdLogin());
                        sessao.setAttribute("aluno", aluno.getId());
                        sessao.setAttribute("curso", aluno.getIdCurso());
                    }
                    sessao.setAttribute("menu", pag);
                    RequestDispatcher rd =
                            req.getRequestDispatcher("index.jsp");
                    rd.forward(req, resp);
                } catch (ServletException ex) {
                    Logger.getLogger(LoginLogica.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LoginLogica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{                
                try {
                    //JOptionPane.showMessageDialog(null, "Login/Senha incrorreto.", "Erro", JOptionPane.ERROR_MESSAGE);
                    req.setAttribute("erroLog", "Login/Senha incrorreto.");
                    RequestDispatcher rd =
                            req.getRequestDispatcher("Login.jsp");
                    rd.forward(req, resp);
                } catch (ServletException ex) {
                    Logger.getLogger(LoginLogica.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LoginLogica.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        }else if( metodo.equals("consultar") ){
            try {
                String campo = req.getParameter("campo");
                String valor = req.getParameter("valor");
                if( campo.equals("IdPerfil") ){
                    valor = req.getParameter("valor2");
                }
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
                        html.println("<label><b>Login &nbsp;</b></label>");
                        html.println("</td>");
                        html.println("<td>");
                        html.println("<label><b>Perfil &nbsp;</b></label>");
                        html.println("</td>");                        
                        html.println("</tr>");
                        html.println("</thead>");
                        html.println("<tbody>");
                        for( Login l:list){
                                                        
                            html.println("<tr class='"+ css[cont%2] +"'>");
                            //html.println("<tr class='hilight'>");
                            html.println("<td>");
                            html.println(l.getLogin());
                            html.println("</td>");
                            html.println("<td>");                            
                            html.println(l.getPerfil().getDescricao());
                            html.println("</td>");                            
                            html.println("<td>");
                            html.println("<a href=LoginAltera.jsp?id=" + l.getIdLogin() + " >Alterar</a>");
                            html.println("<a href=# onClick='confirma(" + l.getIdLogin()+ ")' >Excluir</a>");
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
                req.getRequestDispatcher("LoginConsulta.jsp");
                        
            if( metodo.equals("verifica")){
               String ra="";
                System.out.println("###################Logica -> Verifica: " + req.getParameter("RA") );
                if( req.getParameter("RA")!=null ){
                    System.out.println("###################Logica -> É aluno");
                    ra = req.getParameter("RA");

                }else{
                    System.out.println("###################Logica -> É normal");
                    ra = req.getParameter("Login");
                }
                if( dao.verifica(ra)==true ){
                    System.out.println("###################Logica -> Usuário existe");
                   try {
                       PrintWriter html;
                       html = resp.getWriter();
                       html.println("Usu&aacuterio j&aacute existe!");
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
                    String login = req.getParameter("Login");            
                    String senha = req.getParameter("Senha2");                            
                    int perfil = Integer.parseInt(req.getParameter("perfil"));
                    System.out.println("###################Logica -> Perfil "+perfil);
                    Login log = new Login(login, senha);                
                    log.setPerfil(daoPerfil.consulta(perfil));

                    System.out.println("###################Logica -> Atribui atributos - FIM");
                    if( metodo.equals("inserir") ){
                        String ra="";
                        int curso=-1;
                        System.out.println("###################Logica -> Inserir");

                        if(log.getPerfil().getDescricao().equals("Aluno") ){                            
                            ra = req.getParameter("RA");
                            log.setLogin(req.getParameter("RA"));
                            curso = Integer.parseInt(req.getParameter("curso"));
                            System.out.println("###################Logica -> É aluno: RA="+ra+" Curso="+curso);
                        }

                        try {                    
                            dao.inserir(log);
                            if(log.getPerfil().getDescricao().equals("Aluno")){ 
                                
                                if(!daoAluno.verifica(ra)){ 
                                    daoAluno.inserirPreCad(ra, curso);
                                }else{
                                    daoAluno.atrelarLogin(ra);
                                }
                            
                            }

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
                            int id = Integer.parseInt(req.getParameter("IdLogin"));                                        
                            try {                        
                                log.setIdLogin(id);
                                dao.alterar(log);                        
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
