package com.TCC.banco_de_ideias_back.dto;

import com.TCC.banco_de_ideias_back.model.Professor;

public class ProfessorDTO {

    private Long id;
    private String nome;
    private String email;
    private String matricula;
    private String departamento;

    public ProfessorDTO() {}

    // Construtor que converte uma entidade Professor para DTO
    public ProfessorDTO(Professor professor) {
        this.id = professor.getId();
        this.nome = professor.getUsuario().getNome();
        this.email = professor.getUsuario().getEmail();
        this.matricula = professor.getUsuario().getMatricula();
        this.departamento = professor.getDepartamento();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getMatricula() { return matricula; }
    public String getDepartamento() { return departamento; }

}
