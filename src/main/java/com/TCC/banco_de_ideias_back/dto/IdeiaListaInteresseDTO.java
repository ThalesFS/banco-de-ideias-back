package com.TCC.banco_de_ideias_back.dto;

public class IdeiaListaInteresseDTO {
    private Long id;
    private String titulo;

    public IdeiaListaInteresseDTO(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
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
}
