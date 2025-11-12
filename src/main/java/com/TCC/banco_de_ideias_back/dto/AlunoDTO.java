package com.TCC.banco_de_ideias_back.dto;

import com.TCC.banco_de_ideias_back.model.Aluno;

public class AlunoDTO {

    private Long id;
    private String nome;
    private String email;
    private String matricula;
    private String curso;

    public AlunoDTO(){}

    // Construtor que converte uma entidade Aluno para DTO
    public AlunoDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getUsuario().getNome();
        this.email = aluno.getUsuario().getEmail();
        this.matricula = aluno.getUsuario().getMatricula();
        this.curso = aluno.getCurso();
    }

    // foi preciso adicionar os getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getMatricula() { return matricula; }
    public String getCurso() { return curso; }
}
