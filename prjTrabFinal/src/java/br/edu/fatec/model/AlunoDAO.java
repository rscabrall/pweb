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
        System.out.println("###############################Passou no DAO");
        
                                    
        if( (campo == null || campo.equals("")) || (valor == null || valor.equals("")) ){
            System.out.println("###############################Passou no DAO - Pega Tudo");
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
        System.out.println("###############################Passou no DAO - Executou query");    
        while( rs.next() ) {
            aluno = new Aluno (rs.getInt("IdCurso"), 
                    rs.getString("Nome"));
            //System.out.println("###############################Passou no DAO - Instanciou objeto");
            //Falta consultar o curso, mas isso é só um teste
            aluno.setId(rs.getInt("IdAluno"));
           // System.out.println("###############################Passou no DAO - IdAluno:"+aluno.getId());
            aluno.setCPF(rs.getString("CPF"));
            //System.out.println("###############################Passou no DAO - CPF:"+aluno.getCPF());
            aluno.setRA(rs.getString("RA"));
            //System.out.println("###############################Passou no DAO - RA:"+aluno.getRA());
            aluno.setRG(rs.getString("RG"));            
            //System.out.println("###############################Passou no DAO - RG:"+aluno.getRG());
            aluno.setEndereco(rs.getString("Endereco"));
            //System.out.println("###############################Passou no DAO - Ender:"+aluno.getEndereco());
            //aluno.setDtNasc(String.valueOf(rs.getDate("DtNasc")));                        
            aluno.setDtNasc( sdf.format( rs.getDate("DtNasc") )) ;
            //System.out.println("###############################Passou no DAO - Data:"+aluno.getDtNasc());
            LoginDAO log = new LoginDAO();
            aluno.setLogin(log.pegaLogin(rs.getInt("IdLogin")) );
            list.add(aluno);
            System.out.println("###############################Passou no DAO - Setou atributos");
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
    
    public void inserirPreCad(String ra, int curso ) throws SQLException{
        try {
            PreparedStatement ps = null;
            Connection con = super.getCon();
            int cont=0;
            java.util.Date date1;
            java.sql.Date date2 = null;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            date1 = format.parse("01/01/1900");
            java.sql.Date dataBanco = new java.sql.Date(date1.getTime());
            String sql = "INSERT INTO tbAluno(RA, DtNasc, IdCurso, IdLogin) VALUES( ?, ?, (SELECT IdLogin FROM tbLogin WHERE Login=?) )";
            System.out.println("###############################DAO -> Inserir PreCad Antes ");
            ps = con.prepareStatement(sql);
            ps.setString(++cont,  ra );
            ps.setInt(++cont, curso);
            ps.setString(++cont,  ra );
            ps.execute();
            System.out.println("###############################DAO -> Inserir PreCad depois ");
        } catch (ParseException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atrelarLogin(String ra ) throws SQLException{
        PreparedStatement ps = null;
        Connection con = super.getCon();
        int cont=0;
        String sql = "UPDATE tbAluno SET IdLogin=(SELECT IdLogin FROM tbLogin WHERE Login=?) WHERE RA=?";
        System.out.println("###############################DAO -> Atrelar Login ao Aluno já existente Antes ");
        ps = con.prepareStatement(sql);
        ps.setString(++cont,  ra );        
        ps.setString(++cont,  ra );
        ps.execute();
        System.out.println("###############################DAO -> Atrelar Login ao Aluno já existente Depois ");
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
    public boolean verifica(String id){
        try {
            PreparedStatement ps = null;
            Connection con = super.getCon();
            int cont=0;
            String sql = "SELECT RA FROM tbAluno WHERE RA=?";
            
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
    
    public Aluno pegaID(int id){
        Aluno alu=null;
        try {
            PreparedStatement ps = null;
            Connection con = super.getCon();
            int cont=0;
            String sql = "SELECT IdAluno, IdCurso FROM tbAluno WHERE IdLogin=?";
            
            ps = con.prepareStatement(sql);
            ps.setInt(++cont, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                alu = new Aluno(rs.getInt("IdCurso"), "");
                alu.setId(rs.getInt("IdAluno"));
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alu;
    }
    
}
            
    
