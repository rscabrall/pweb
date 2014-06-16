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
public class AlunoDisciplina {
    private Aluno aluno;
    private Disciplina disciplina;
    private int Faltas;
    private Double Nota;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public int getFaltas() {
        return Faltas;
    }

    public void setFaltas(int Faltas) {
        this.Faltas = Faltas;
    }

    public Double getNota() {
        return Nota;
    }

    public void setNota(Double Nota) {
        this.Nota = Nota;
    }
    
    
    
}

