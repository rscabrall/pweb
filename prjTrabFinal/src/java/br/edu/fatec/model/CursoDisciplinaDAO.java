/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class CursoDisciplinaDAO extends AbstractDAO{
    
    public ArrayList<Disciplina> consultar(String campo, String valor) throws SQLException{
        String sql = "";        
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        PreparedStatement ps = null;        
        Connection con = super.getCon();        
        ArrayList<Disciplina> list = new ArrayList<Disciplina>();
        Disciplina disc = null;
        int cont=0;
        System.out.println("###############################Passou no DAO");
                                                          
        System.out.println("###############################DAO -> Curso não ID");
        sql = "SELECT tbCursoDisciplina.*, tbDisciplina.Nome, tbDisciplina.Carga_Horaria  FROM tbDisciplina, tbCursoDisciplina WHERE tbDisciplina.IdDisciplina=tbCursoDisciplina.IdDisciplina AND IdCurso=?";        
        ps = con.prepareStatement(sql);
        ps.setInt(++cont, Integer.parseInt(valor));
                                    
        ResultSet rs = ps.executeQuery();

        while( rs.next() ) {
            disc = new Disciplina(rs.getString("IdDisciplina")); 
            disc.setNome(rs.getString("Nome"));
            disc.setCarga_Horaria(rs.getString("Carga_Horaria"));
                                    
            list.add(disc);
        }
        return list;
    }
    
    public void inserir(CursoDisciplina curso_disc) throws SQLException{
        PreparedStatement ps = null;
        Connection con = super.getCon();
        int cont=0;
        String sql = "INSERT INTO tbCursoDisciplina(IdCurso, IdDisciplina, SemestreCurso) VALUES( ?, ?, ? )";
        System.out.println("###############################DAO -> Inserir Antes ");
        ps = con.prepareStatement(sql);
        ps.setInt(++cont,  curso_disc.getIdCurso());
        ps.setString(++cont,  curso_disc.getIdDisciplina());
        ps.setInt(++cont,  curso_disc.getSemestre());
        ps.execute();
        System.out.println("###############################DAO -> Inserir depois ");
    }
           
    public void excluir(int idCurs, String idDisc) throws SQLException{
        PreparedStatement ps = null;        
        Connection con = super.getCon();                
        int cont=0;
        String sql = "DELETE FROM tbCursoDisciplina WHERE IdCurso=? AND IdDisciplina=?";
            
            ps = con.prepareStatement(sql);            
            ps.setInt(++cont,  idCurs);
            ps.setString(++cont,  idDisc);
            ps.execute();
               
        System.out.println("###############################Passou no DAO Excluir");        
    }
}
