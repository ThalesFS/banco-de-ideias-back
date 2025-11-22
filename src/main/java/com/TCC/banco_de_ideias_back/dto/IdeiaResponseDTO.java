package com.TCC.banco_de_ideias_back.dto;

import com.TCC.banco_de_ideias_back.model.*;

public class IdeiaResponseDTO {

    private Long id;
    private Long professorId;
    private String titulo;
    private String descricao;
    private String cursos;
    private String tecnologias;
    private String status;
    private String professorNome;
    private String professorDepartamento;

    public IdeiaResponseDTO(Ideia ideia) {
        this.id = ideia.getId();
        this.professorId = ideia.getProfessor().getId();
        this.titulo = ideia.getTitulo();
        this.descricao = ideia.getDescricao();
        this.cursos = ideia.getCursos();
        this.tecnologias = ideia.getTecnologias();
        this.status = ideia.getStatus().name();
        this.professorNome = ideia.getProfessor().getUsuario().getNome();
        this.professorDepartamento = ideia.getProfessor().getDepartamento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}
