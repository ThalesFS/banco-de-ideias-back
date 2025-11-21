package com.TCC.banco_de_ideias_back.dto;

public class AlunoInteressadoDTO {
    private Long alunoId;
    private String nome;
    private String email;
    private String curso;

    public AlunoInteressadoDTO(Long alunoId, String nome, String email, String curso) {
        this.alunoId = alunoId;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
    }

    public Long getAlunoId() { return alunoId; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCurso() { return curso; }
}
