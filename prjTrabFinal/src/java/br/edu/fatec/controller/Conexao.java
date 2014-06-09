/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Rafael
 */
public class Conexao {
    private String DataSource="jdbc/RAM_Con";
    private Connection con=null;
    private String ContextIni = "java:/comp/env";
    
    public Connection conectar() throws SQLException{
        Context envContext = null;
        try {
            envContext = new InitialContext();
            Context initConntext = (Context)envContext.lookup(ContextIni);
            DataSource ds = (DataSource) initConntext.lookup(this.DataSource);
            con = ds.getConnection();
            
        } catch (NamingException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }
    public void fecharConexao(){
        if (con != null){
	       try {
                  con.close();
           } catch (SQLException ex) {
                   System.out.println(ex.toString());    
           }
        }   
    }

    public String getDataSource() {
        return DataSource;
    }

    public void setDataSource(String DataSource) {
        this.DataSource = DataSource;
    }
    
    
}
