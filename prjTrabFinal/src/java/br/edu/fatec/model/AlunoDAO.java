/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.model;

//import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
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
 
    public ArrayList<Aluno> consultar(String campo, String valor) throws SQLException{
        String sql = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        PreparedStatement ps = null;        
        Connection con = super.getCon();        
        ArrayList<Aluno> list = new ArrayList<Aluno>();
        Aluno aluno = null;
        int cont=0;
        //System.out.println("###############################Passou no DAO");
        
                                    
        if( (campo == null || campo.equals("")) || (valor == null || valor.equals("")) ){
            sql = "SELECT *, FORMAT(DtNasc, 'dd/MM/yyyy') as DtNasc FROM tbAluno ORDER BY Nome, RA";
            ps = con.prepareStatement(sql);                
        }else{
            if( valor.equals("") == false){                     

                if( campo.equals("Id") == false){                        
                    sql = "SELECT *, FORMAT(DtNasc, 'dd/MM/yyyy') as DtNasc FROM tbAluno WHERE " + campo +" LIKE ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(++cont, String.valueOf("%"+valor+"%"));                
                }else{
                    sql = "SELECT *, FORMAT(DtNasc, 'dd/MM/yyyy') as DtNasc FROM tbAluno WHERE IdAluno=?";
                    ps = con.prepareStatement(sql); 
                    ps.setInt(++cont, Integer.parseInt(valor));
                }                
            }

            System.out.println("SQL: " +ps.toString() + " valor: " + valor );
            System.out.println("SQL: " + sql);
        }        

        ResultSet rs = ps.executeQuery();

        while( rs.next() ) {
            aluno = new Aluno (rs.getInt("idCurso"), 
                    rs.getString("Nome"));
            //Falta consultar o curso, mas isso é só um teste
            aluno.setId(rs.getInt("IdAluno"));
            aluno.setCPF(rs.getString("CPF"));
            aluno.setRA(rs.getString("RA"));
            aluno.setRG(rs.getString("RG"));
            aluno.setEndereco(rs.getString("Endereco"));
            //aluno.setDtNasc(String.valueOf(rs.getDate("DtNasc")));
            aluno.setDtNasc( sdf.format( rs.getDate("DtNasc") )) ;
            list.add(aluno);
        }
        return list;
            
    }
        
    public void inserir(Aluno alu) throws SQLException{
        try {
            PreparedStatement ps = null;
            Connection con = super.getCon();
            int cont=0;
            java.util.Date date1;
            java.sql.Date date2 = null;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            date1 = format.parse(alu.getDtNasc());
            java.sql.Date dataBanco = new java.sql.Date(date1.getTime()); 
            String sql = "INSERT INTO tbAluno(RA, RG, CPF, Nome, Endereco, DtNasc, IdCurso) VALUES( ?, ?, ?, ?, ?, ?, ? )";
            
            System.out.println("###############################DAO -> Inserir Antes ");
            ps = con.prepareStatement(sql);
            ps.setString(++cont,  alu.getRA() );
            ps.setString(++cont,  alu.getRG());
            ps.setString(++cont,  alu.getCPF());
            ps.setString(++cont,  alu.getNome());
            ps.setString(++cont,  alu.getEndereco());
            ps.setDate(++cont, (Date)dataBanco);
            ps.setInt(++cont, alu.getIdCurso()); //Talves troque para uma associação
            
            ps.execute();
            System.out.println("###############################DAO -> Inserir depois ");
            
            //System.out.println("###############################Passou no DAO");        
        } catch (ParseException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void alterar(Aluno alu) throws SQLException{
        try {
            PreparedStatement ps = null;
            Connection con = super.getCon();
            int cont=0;
            System.out.println("###############################DAO -> Data ");
            java.util.Date date1;
            java.sql.Date date2 = null;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            
            date1 = format.parse(alu.getDtNasc());
            //date2 = new java.sql.Date(date1.getDay(), date1.getMonth(), date1.getYear());
            
            //java.util.Date data = new SimpleDateFormat("dd/MM/yyyy").parse(alu.getDtNasc());  
            java.sql.Date dataBanco = new java.sql.Date(date1.getTime()); 
            
            System.out.println("###############################DAO -> Data1 "+date1 + "Data2 "+dataBanco );
            
            String sql = "UPDATE tbAluno SET RA=?, RG=?, CPF=?, Nome=?, Endereco=?, DtNasc=?, IdCurso=? "
                    + "WHERE IdAluno=?";
            ps = con.prepareStatement(sql);
            ps.setString(++cont,  alu.getRA() );
            ps.setString(++cont,  alu.getRG());
            ps.setString(++cont,  alu.getCPF());
            ps.setString(++cont,  alu.getNome());
            ps.setString(++cont,  alu.getEndereco());
            //System.out.println("###############################DAO -> Data 2");
            ps.setDate(++cont, (Date)dataBanco);
            ps.setInt(++cont, alu.getIdCurso());
            ps.setInt(++cont, alu.getId());
            ps.execute();
            System.out.println("###############################Passou no DAO");
        } catch (ParseException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluir(int id) throws SQLException{
        PreparedStatement ps = null;        
        Connection con = super.getCon();                
        int cont=0;
        String sql = "DELETE FROM tbAluno WHERE IdAluno=?";
            
            ps = con.prepareStatement(sql);            
            ps.setInt(++cont, id);            
            ps.execute();
               
        //System.out.println("###############################Passou no DAO");        
    }
}
            
    
