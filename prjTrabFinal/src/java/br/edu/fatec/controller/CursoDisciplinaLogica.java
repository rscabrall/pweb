/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.controller;

import br.edu.fatec.model.Curso;
import br.edu.fatec.model.CursoDAO;
import br.edu.fatec.model.CursoDisciplina;
import br.edu.fatec.model.CursoDisciplinaDAO;
import br.edu.fatec.model.Disciplina;
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
public class CursoDisciplinaLogica extends AbstractLogica {

    @Override
    public void executa(HttpServletRequest req, HttpServletResponse resp) {
        String metodo = req.getParameter("metodo");
        ArrayList<Disciplina> list = new ArrayList<Disciplina>();
        CursoDisciplinaDAO dao = new CursoDisciplinaDAO();                        
        
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
                    if( list.isEmpty() == true){                        
                    }else{
                        html.print("<label>Disciplina: </label>");
                        html.print("<select id='disciplina' name='disciplina'>");
                        html.print("<option id='-1'>[Selecione]</option>");
                        for( Disciplina d:list){
                            html.print("<option value='"+d.getIdDisciplina()+"'>"+d.getNome()+"</option>");                            
                        }                        
                        html.print("</select>");
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
        
            if( metodo.equals("excluir") ){
                System.out.println("###################Logica -> Excluir");
                String disc = req.getParameter("disc");
                int curso = Integer.parseInt(req.getParameter("curso"));
                req.setAttribute("id", curso);
                try {                    
                    dao.excluir(curso, disc);                    
                    resp.sendRedirect("CursoDisciplina.jsp?id="+curso);
                    //JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso.", "Excluir", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(AlunoLogica.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Erro: " + ex, "Excluir", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(CursoDisciplinaLogica.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else{

                System.out.println("###################Logica -> Incluir");
                String disc = req.getParameter("disciplina");                                    
                int semestre = Integer.parseInt(req.getParameter("Semestre"));
                int curso = Integer.parseInt(req.getParameter("IdCurso"));
                CursoDisciplina curs = new CursoDisciplina();                
                curs.setIdCurso(curso);
                curs.setIdDisciplina(disc);
                curs.setSemestre(semestre);


                System.out.println("###################Logica -> Inserir");
                 RequestDispatcher rd = req.getRequestDispatcher("CursoDisciplina.jsp");
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

            }                
        }

    }
}
