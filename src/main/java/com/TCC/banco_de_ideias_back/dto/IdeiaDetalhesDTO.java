package com.TCC.banco_de_ideias_back.dto;

import com.TCC.banco_de_ideias_back.model.*;

public class IdeiaDetalhesDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String cursos;
    private String tecnologias;
    private StatusIdeia status;

    private String professorNome;
    private String professorDepartamento;
    private String professorEmail;

    public IdeiaDetalhesDTO(Long id,
                            String titulo,
                            String descricao,
                            String cursos,
                            String tecnologias,
                            StatusIdeia status,
                            String professorNome,
                            String professorDepartamento,
                            String professorEmail){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.cursos = cursos;
        this.tecnologias = tecnologias;
        this.status = status;
        this.professorNome = professorNome;
        this.professorDepartamento = professorDepartamento;
        this.professorEmail = professorEmail;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCursos() {
        return cursos;
    }

    public void setCursos(String cursos) {
        this.cursos = cursos;
    }

    public String getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(String tecnologias) {
        this.tecnologias = tecnologias;
    }

    public StatusIdeia getStatus() {
        return status;
    }

    public void setStatus(StatusIdeia status) {
        this.status = status;
    }

    public String getProfessorNome() {
        return professorNome;
    }

    public void setProfessorNome(String professorNome) {
        this.professorNome = professorNome;
    }

    public String getProfessorDepartamento() {
        return professorDepartamento;
    }

    public void setProfessorDepartamento(String professorDepartamento) {
        this.professorDepartamento = professorDepartamento;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public void setProfessorEmail(String professorEmail) {
        this.professorEmail = professorEmail;
    }
}
