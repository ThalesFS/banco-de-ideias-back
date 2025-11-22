package com.TCC.banco_de_ideias_back.dto;

import com.TCC.banco_de_ideias_back.model.*;

public class IdeiaCriacaoDTO {
    private Long professorId;
    private String titulo;
    private String descricao;
    private String cursos;
    private String tecnologias;

    public IdeiaCriacaoDTO(Long professorId,
                           String titulo,
                           String descricao,
                           String cursos,
                           String tecnologias){
        this.professorId = professorId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.cursos = cursos;
        this.tecnologias = tecnologias;
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
}
