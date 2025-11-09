package com.TCC.banco_de_ideias_back.dto;

public class LoginResponseDTO {

    public Long id;
    public String tipo;
    public String nome;

    public LoginResponseDTO(Long id, String nome, String tipo){
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }
}
