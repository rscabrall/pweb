/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.model;

import br.edu.fatec.controller.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Rafael
 */
abstract public class AbstractDAO {
    private Connection con;
    private Conexao link = new Conexao();
    
    public AbstractDAO(){       
        try {
            con = link.conectar();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //abstract public void requisit(HttpServletRequest req, HttpServletResponse resp);

    public Connection getCon() {
        return con;
    }  
    
    public void setCon(String dtSource){
        link.setDataSource(dtSource);
        try {
            this.con = link.conectar();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Conexao getLink() {
        return link;
    }

    public void setLink(Conexao link) {
        this.link = link;
    }
    
}
