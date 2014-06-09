/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.model;

/**
 *
 * @author ad122193
 */
public class Login {
    private int IdLogin;
    private String Login;
    private String Senha;

//  private Perfil perfil;
    public Login(String Login, String Senha) {
        this.Login = Login;
        this.Senha = Senha;
    }

    public int getIdLogin() {
        return IdLogin;
    }

    public void setIdLogin(int IdLogin) {
        this.IdLogin = IdLogin;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }
    
    
}
