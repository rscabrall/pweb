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
public class CursoDisciplina {
    private int IdCurso;
    private String IdDisciplina;
    private int Semestre;

    public int getIdCurso() {
        return IdCurso;
    }

    public void setIdCurso(int IdCurso) {
        this.IdCurso = IdCurso;
    }

    public String getIdDisciplina() {
        return IdDisciplina;
    }

    public void setIdDisciplina(String IdDisciplina) {
        this.IdDisciplina = IdDisciplina;
    }

    public int getSemestre() {
        return Semestre;
    }

    public void setSemestre(int Semestre) {
        this.Semestre = Semestre;
    }
    
    
    
}
