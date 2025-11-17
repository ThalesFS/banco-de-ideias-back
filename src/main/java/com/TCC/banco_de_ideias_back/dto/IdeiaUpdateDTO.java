package com.TCC.banco_de_ideias_back.dto;

import com.TCC.banco_de_ideias_back.model.*;

public class IdeiaUpdateDTO {

    private String titulo;
    private String descricao;
    private String cursos;
    private String tecnologias;
    private StatusIdeia status;

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCursos() {
        return cursos;
    }

    public String getTecnologias() {
        return tecnologias;
    }

    public StatusIdeia getStatus() {
        return status;
    }

}
