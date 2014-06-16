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
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

/**
 *
 * @author ad122193
 */
public class LoginDAO extends AbstractDAO{
    private Criptografia cripto = new Criptografia();
    public ArrayList<Login> consultar(String campo, String valor) throws SQLException{
        String sql = "";
        PerfilDAO daoPerfil = new PerfilDAO();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        PreparedStatement ps = null;        
        Connection con = super.getCon();        
        ArrayList<Login> list = new ArrayList<Login>();
        Login login = null;
        int cont=0;
        System.out.println("###############################Passou no DAO");
        
                                    
        if( (campo == null || campo.equals("")) || (valor == null || valor.equals("")) ){
            sql = "SELECT * FROM tbLogin ORDER BY IdPerfil, Login";
            ps = con.prepareStatement(sql);                
        }else{
            if(campo.equals("Perfil")){
                System.out.println("###############################DAO -> Login PERFIL");
                sql = "SELECT * FROM tbLogin WHERE IdPerfil=? ORDER BY IdPerfil, Login";
                ps = con.prepareStatement(sql);
                ps.setInt(++cont, Integer.parseInt(valor));
            }else{
                                                     
                System.out.println("###############################DAO -> Login");
                sql = "SELECT * FROM tbLogin WHERE " + campo +" LIKE ? ORDER BY IdPerfil, Login";
                ps = con.prepareStatement(sql);
                ps.setString(++cont, String.valueOf("%"+valor+"%"));                

                
            }
            System.out.println("SQL: " +ps.toString() + " valor: " + valor );
            System.out.println("SQL: " + sql);
        }        

        ResultSet rs = ps.executeQuery();

        while( rs.next() ) {
            login = new Login (rs.getString("Login"), rs.getString("Senha")); 
            login.setIdLogin(rs.getInt("IdLogin"));
            //daoPerfil.consulta(rs.getInt("IdPerfil"));
            login.setPerfil(daoPerfil.consulta(rs.getInt("IdPerfil")));
            //disciplina.setNome(rs.getString("Nome"));
            //disciplina.setCarga_Horaria(rs.getString("Carga_Horaria"));            
            list.add(login);
        }
        return list;
            
    }
   
    public void inserir(Login login) throws SQLException{
        PreparedStatement ps = null;
        Connection con = super.getCon();
        int cont=0;
        String sql = "INSERT INTO tbLogin(Login, Senha, IdPerfil) VALUES( ?, ?, ? )";
        System.out.println("###############################DAO -> Inserir Antes ");
        ps = con.prepareStatement(sql);
        ps.setString(++cont,  login.getLogin());
        ps.setString(++cont,  cripto.criptografar(login.getSenha()) );
        ps.setInt(++cont,  login.getPerfil().getIdPerfil());
        ps.execute();
        System.out.println("###############################DAO -> Inserir depois ");
    }
   
    public void alterar(Login login) throws SQLException{
        PreparedStatement ps = null;
        Connection con = super.getCon();
        String senha;
        int cont=0;
        String sql = "UPDATE tbLogin SET Login=?, Senha=? "
                + "WHERE IdLogin=?";
        
        if( login.getSenha().equals("") || login.getSenha() == null ){
            System.out.println("###############################DAO Login -> Sem senha");
            sql = "UPDATE tbLogin SET Login=? "
                + "WHERE IdLogin=?";
            
            ps = con.prepareStatement(sql);
            ps.setString(++cont,  login.getLogin());                        
            ps.setInt(++cont, login.getIdLogin());
            
        }else{
            System.out.println("###############################DAO Login -> Com senha");
            ps = con.prepareStatement(sql);
            ps.setString(++cont,  login.getLogin());
            senha = cripto.criptografar(login.getSenha());
            ps.setString(++cont,  senha);            
            ps.setInt(++cont, login.getIdLogin());
        }                
        
        ps.execute();
        System.out.println("###############################Passou no DAO");
    }
    
    public void excluir(int id) throws SQLException{
        PreparedStatement ps = null;        
        Connection con = super.getCon();                
        int cont=0;
        String sql = "DELETE FROM tbLogin WHERE IdLogin=?";
            
            ps = con.prepareStatement(sql);            
            ps.setInt(++cont, id);            
            ps.execute();
               
        System.out.println("###############################Passou no DAO Excluir");        
    }
     
    public Login pegaLogin(int id){
        Login log=null;
        PerfilDAO daoPerfil = new PerfilDAO();
        try {
            PreparedStatement ps = null;
            Connection con = super.getCon();
            int cont=0;
            String sql = "SELECT * FROM tbLogin WHERE IdLogin=?";
            
            System.out.println("###############################DAO Login Verifica: "+id);        
            ps = con.prepareStatement(sql);
            ps.setInt(++cont, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next() == true){
                System.out.println("###############################DAO Login existe");        
                log = new Login(rs.getString("Login"), rs.getString("Senha"));
                log.setIdLogin(rs.getInt("IdLogin"));
                log.setPerfil(daoPerfil.consulta(rs.getInt("IdPerfil")));
                                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return log;
    }
    
    public boolean verifica(String login){
        try {
            PreparedStatement ps = null;
            Connection con = super.getCon();
            int cont=0;
            String sql = "SELECT Login FROM tbLogin WHERE Login=?";
            
            System.out.println("###############################DAO Login Verifica: "+login);        
            ps = con.prepareStatement(sql);
            ps.setString(++cont, login);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next() == true){
                System.out.println("###############################DAO Login Já existe");        
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //Valta mexer
    public int validaLogin(String login, String senha){
        try {
            PreparedStatement ps = null;
            Connection con = super.getCon();
            int cont=0;
            int id=-1;
            String sql = "SELECT * FROM tbLogin WHERE Login=? AND Senha=?";
            
            ps = con.prepareStatement(sql);
            ps.setString(++cont, login);
            ps.setString(++cont, cripto.criptografar(senha));
            ResultSet rs = ps.executeQuery();
            
            while(rs.next() ){
                return rs.getInt("IdLogin");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
}
