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
public class AlunoDisciplinaDAO extends AbstractDAO {
    public ArrayList<AlunoDisciplina> consultar(String valor) throws SQLException{
        String sql = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        PreparedStatement ps = null;        
        Connection con = super.getCon();        
        ArrayList<AlunoDisciplina> list = new ArrayList<AlunoDisciplina>();
        ArrayList<Aluno> listAlu = new ArrayList<Aluno>();
        ArrayList<Disciplina> listDisc = new ArrayList<Disciplina>();
        AlunoDisciplina aluno = null;
        AlunoDAO daoAlu = new AlunoDAO();
        DisciplinaDAO daoDisc = new DisciplinaDAO();
        
        int cont=0;
        System.out.println("###############################Passou no DAO");
        
                                    
        if( (valor == null || valor.equals("")) ){
            System.out.println("###############################Passou no DAO - Pega Tudo");
            sql = "SELECT * FROM AlunoDisciplina";
            ps = con.prepareStatement(sql);                            
        }else{                            
            System.out.println("###############################Passou no DAO - IdDisc");    
            sql = "SELECT * FROM tbAlunoDisciplina WHERE IdDisciplina=?";
            ps = con.prepareStatement(sql);
            ps.setString(++cont, valor); 
        }        

        ResultSet rs = ps.executeQuery();
        System.out.println("###############################Passou no DAO - Executou query");    
        while( rs.next() ) {
            aluno = new AlunoDisciplina();                     
            //System.out.println("###############################Passou no DAO - Instanciou objeto");
            //Falta consultar o curso, mas isso é só um teste
            listAlu = daoAlu.consultar("Id", String.valueOf(rs.getInt("IdAluno")));
            aluno.setAluno(listAlu.get(0));
            listDisc = daoDisc.consultar("IdDisciplina", rs.getString("IdDisciplina"));
            aluno.setDisciplina(listDisc.get(0));
            aluno.setFaltas(rs.getInt("Faltas"));
            aluno.setNota(rs.getDouble("Nota"));
            list.add(aluno);
            System.out.println("###############################Passou no DAO AluDisc - Setou atributos");
        }
        return list;
            
    }
                               
    public void alterar(AlunoDisciplina alu, int al, String disc) throws SQLException{
        PreparedStatement ps = null;
        Connection con = super.getCon();
        int cont=0;
        String sql = "UPDATE tbAlunoDisciplina SET Nota=?, Faltas=? "
                + "WHERE IdAluno=? AND IdDisciplina=?";
        ps = con.prepareStatement(sql);
        ps.setInt(++cont,  alu.getFaltas() );
        ps.setDouble(++cont,  alu.getNota());
        ps.setInt(++cont,  al);
        ps.setString(++cont,  disc);
        ps.execute();
        System.out.println("###############################Passou no DAO AluDisc");
        
    }
        
    
}
