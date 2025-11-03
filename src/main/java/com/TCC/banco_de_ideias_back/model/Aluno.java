package com.TCC.banco_de_ideias_back.model;

import jakarta.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    private Long id; // mesmo ID do usuario

    private String curso;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private  Usuario usuario;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public Usuario getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
}
