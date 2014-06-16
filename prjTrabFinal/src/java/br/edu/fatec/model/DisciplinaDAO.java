/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class DisciplinaDAO extends AbstractDAO {
    public ArrayList<Disciplina> consultar(String campo, String valor) throws SQLException{
        String sql = "";
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        PreparedStatement ps = null;        
        Connection con = super.getCon();        
        ArrayList<Disciplina> list = new ArrayList<Disciplina>();
        Disciplina disciplina = null;
        int cont=0;
        System.out.println("###############################Passou no DAO");
        
                                    
        if( (campo == null || campo.equals("")) || (valor == null || valor.equals("")) ){
            sql = "SELECT * FROM tbDisciplina ORDER BY Nome";
            ps = con.prepareStatement(sql);                
        }else{
            if( valor.equals("") == false){                     

                if( campo.equals("IdDisciplina") == false){                        
                    System.out.println("###############################DAO -> Nome");
                    sql = "SELECT * FROM tbDisciplina WHERE " + campo +" LIKE ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(++cont, String.valueOf("%"+valor+"%"));                
                }else{
                    System.out.println("###############################DAO -> Id");
                    sql = "SELECT * FROM tbDisciplina WHERE IdDisciplina=?";
                    ps = con.prepareStatement(sql); 
                    ps.setString(++cont, valor);
                }                
            }

            System.out.println("SQL: " +ps.toString() + " valor: " + valor );
            System.out.println("SQL: " + sql);
        }        

        ResultSet rs = ps.executeQuery();

        while( rs.next() ) {
            disciplina = new Disciplina (rs.getString("IdDisciplina")); 
                    
            disciplina.setNome(rs.getString("Nome"));
            disciplina.setCarga_Horaria(rs.getString("Carga_Horaria"));
            
            list.add(disciplina);
        }
        return list;
            
    }
   
    public void inserir(Disciplina disc) throws SQLException{
        PreparedStatement ps = null;
        Connection con = super.getCon();
        int cont=0;
        String sql = "INSERT INTO tbDisciplina(IdDisciplina, Nome, Carga_Horaria) VALUES( ?, ?, ? )";
        System.out.println("###############################DAO -> Inserir Antes ");
        ps = con.prepareStatement(sql);
        ps.setString(++cont,  disc.getIdDisciplina() );
        ps.setString(++cont,  disc.getNome());
        ps.setString(++cont,  disc.getCarga_Horaria());
        ps.execute();
        System.out.println("###############################DAO -> Inserir depois ");
    }
   
    public void alterar(Disciplina disc) throws SQLException{
        PreparedStatement ps = null;
        Connection con = super.getCon();
        int cont=0;
        String sql = "UPDATE tbDisciplina SET Nome=?, Carga_Horaria=? "
                + "WHERE IdDisciplina=?";
        ps = con.prepareStatement(sql);
        ps.setString(++cont,  disc.getNome());
        ps.setString(++cont,  disc.getCarga_Horaria());
        ps.setString(++cont,  disc.getIdDisciplina());
        ps.execute();
        System.out.println("###############################Passou no DAO");
    }
    
    public void excluir(String id) throws SQLException{
        PreparedStatement ps = null;        
        Connection con = super.getCon();                
        int cont=0;
        String sql = "DELETE FROM tbDisciplina WHERE IdDisciplina=?";
            
            ps = con.prepareStatement(sql);            
            ps.setString(++cont, id);            
            ps.execute();
               
        System.out.println("###############################Passou no DAO Excluir");        
    }
    
    public boolean verifica(String id){
        try {
            PreparedStatement ps = null;
            Connection con = super.getCon();
            int cont=0;
            String sql = "SELECT IdDisciplina FROM tbDisciplina WHERE IdDisciplina=?";
            
            ps = con.prepareStatement(sql);
            ps.setString(++cont, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next() == true)
                return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
