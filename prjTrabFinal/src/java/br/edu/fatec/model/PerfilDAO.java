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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class PerfilDAO extends AbstractDAO {
    
    public Perfil consulta(int id){
        Perfil perfil=null;
        try {
            PreparedStatement ps = null;
            Connection con = super.getCon();
            int cont=0;
            String sql = "SELECT * FROM tbPerfil WHERE IdPerfil=?";
            
            ps = con.prepareStatement(sql);
            ps.setInt(++cont, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next() == true)
                perfil = new Perfil(rs.getInt("IdPerfil"), rs.getString("Descricao"));
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return perfil;
    }
    
}
