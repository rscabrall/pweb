/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.model;

/**
 *
 * @author Rafael
 */
public class Disciplina {
    private String IdDisciplina;
    private String Nome;
    private String Carga_Horaria;
    
    public Disciplina(String IdDisciplina){
        this.IdDisciplina = IdDisciplina;
    }
    
    public String getIdDisciplina() {
        return IdDisciplina;
    }

    public void setIdDisciplina(String IdDisciplina) {
        this.IdDisciplina = IdDisciplina;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCarga_Horaria() {
        return Carga_Horaria;
    }

    public void setCarga_Horaria(String Carga_Horaria) {
        this.Carga_Horaria = Carga_Horaria;
    }
    
    
    
}
