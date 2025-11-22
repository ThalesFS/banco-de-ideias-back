package com.TCC.banco_de_ideias_back.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ideia")
public class Ideia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    private String titulo;
    @Column(length = 3000)
    private String descricao;
    private String cursos;
    private String tecnologias;

    @Enumerated(EnumType.STRING)
    private StatusIdeia status = StatusIdeia.ABERTA;


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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
