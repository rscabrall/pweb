/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.model;

import br.edu.fatec.utilitarios.Criptografia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class CursoDAO extends AbstractDAO {
    private Criptografia cripto = new Criptografia();
    public ArrayList<Curso> consultar(String campo, String valor) throws SQLException{
        String sql = "";        
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        PreparedStatement ps = null;        
        Connection con = super.getCon();        
        ArrayList<Curso> list = new ArrayList<Curso>();
        Curso curso = null;
        int cont=0;
        System.out.println("###############################Passou no DAO");
                                            
        if( (campo == null || campo.equals("")) || (valor == null || valor.equals("")) ){
            sql = "SELECT * FROM tbCurso ORDER BY Nome, Modalidade";
            ps = con.prepareStatement(sql);
        }else{
            if( valor.equals("") == false){                     
                
                if( campo.equals("IdCurso") ){
                    System.out.println("###############################DAO -> Curso não ID");
                    sql = "SELECT * FROM tbCurso WHERE " + campo +"=? ORDER BY Nome, Modalidade";
                    ps = con.prepareStatement(sql);
                    ps.setInt(++cont, Integer.parseInt(valor));
                }else{
                    System.out.println("###############################DAO -> Curso não ID");
                    sql = "SELECT * FROM tbCurso WHERE " + campo +" LIKE ? ORDER BY Nome, Modalidade";
                    ps = con.prepareStatement(sql);
                    ps.setString(++cont, String.valueOf("%"+valor+"%"));                
                }
            }

            System.out.println("SQL: " +ps.toString() + " valor: " + valor );
            System.out.println("SQL: " + sql);
        }        

        ResultSet rs = ps.executeQuery();

        while( rs.next() ) {
            curso = new Curso(); 
            curso.setIdCurso(rs.getInt("IdCurso"));
            curso.setNome(rs.getString("Nome"));
            curso.setModalidade(rs.getString("Modalidade"));
            curso.setDuracao(rs.getString("Duracao"));
                        
            list.add(curso);
        }
        return list;
            
    }
   
    public void inserir(Curso curso) throws SQLException{
        PreparedStatement ps = null;
        Connection con = super.getCon();
        int cont=0;
        String sql = "INSERT INTO tbCurso(Nome, Modalidade, Duracao) VALUES( ?, ?, ? )";
        System.out.println("###############################DAO -> Inserir Antes ");
        ps = con.prepareStatement(sql);
        ps.setString(++cont,  curso.getNome());
        ps.setString(++cont,  curso.getModalidade() );
        ps.setString(++cont,  curso.getDuracao());
        ps.execute();
        System.out.println("###############################DAO -> Inserir depois ");
    }
   
    public void alterar(Curso curso) throws SQLException{
        PreparedStatement ps = null;
        Connection con = super.getCon();        
        int cont=0;
        String sql = "UPDATE tbCurso SET Nome=?, Modalidade=?, Duracao=? "
                + "WHERE IdCurso=?";
        
        
        System.out.println("###############################DAO Curso");
        ps = con.prepareStatement(sql);        
        ps.setString(++cont,  curso.getNome());
        ps.setString(++cont,  curso.getModalidade());
        ps.setString(++cont,  curso.getDuracao());            
        ps.setInt(++cont, curso.getIdCurso());
                    
        
        ps.execute();
        System.out.println("###############################Passou no DAO");
    }
    
    public void excluir(int id) throws SQLException{
        PreparedStatement ps = null;        
        Connection con = super.getCon();                
        int cont=0;
        String sql = "DELETE FROM tbCurso WHERE IdCurso=?";
            
            ps = con.prepareStatement(sql);            
            ps.setInt(++cont, id);            
            ps.execute();
               
        System.out.println("###############################Passou no DAO Excluir");        
    }
         
    public boolean verifica(int id){
        try {
            PreparedStatement ps = null;
            Connection con = super.getCon();
            int cont=0;
            String sql = "SELECT IdCurso FROM tbCurso WHERE IdCurso=?";
            
            System.out.println("###############################DAO Curso Verifica: "+id);        
            ps = con.prepareStatement(sql);
            ps.setInt(++cont, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next() == true){
                System.out.println("###############################DAO Curso Já existe");        
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
