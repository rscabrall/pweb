/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.model;

//import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AD122176
 */
public class AlunoDAO extends AbstractDAO {
 
    public ArrayList<Aluno> consultar(String campo, String valor){
        String sql = "";
        PreparedStatement ps = null;        
        Connection con = super.getCon();        
        ArrayList<Aluno> list = new ArrayList<Aluno>();
        Aluno aluno = null;
        int cont=0;
        System.out.println("###############################Passou no DAO");
        try {
                                    
            if( (campo == null || campo.equals("")) || (valor == null || valor.equals("")) ){
                sql = "SELECT * FROM tbAluno ORDER BY Nome, RA";
                ps = con.prepareStatement(sql);                
            }else{
                if( valor.equals("") == false){                     
                    
                    if( campo.equals("Id") == false){                        
                        sql = "SELECT * FROM tbAluno WHERE " + campo +" LIKE ?";
                        ps = con.prepareStatement(sql);
                        ps.setString(++cont, String.valueOf("%"+valor+"%"));                
                    }else{
                        sql = "SELECT * FROM tbAluno WHERE IdAluno=?";
                        ps = con.prepareStatement(sql); 
                        ps.setInt(++cont, Integer.parseInt(valor));
                    }                
                }
                                                                                    
                System.out.println("SQL: " +ps.toString() + " valor: " + valor );
                System.out.println("SQL: " + sql);
            }        
                        
            ResultSet rs = ps.executeQuery();
                        
            while( rs.next() ) {
                aluno = new Aluno (rs.getInt("idCurso"), rs.getInt("IdAluno"), 
                        rs.getString("Nome"));
                //Falta consultar o curso, mas isso é só um teste
                aluno.setCPF(rs.getString("CPF"));
                aluno.setRA(rs.getString("RA"));
                aluno.setRG(rs.getString("RG"));
                aluno.setEndereco(rs.getString("Endereco"));
                aluno.setDtNasc(String.valueOf(rs.getDate("DtNasc")));
                
                list.add(aluno);
            }
            
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
        
        return list;
        
        
    }
        
}
            
    
