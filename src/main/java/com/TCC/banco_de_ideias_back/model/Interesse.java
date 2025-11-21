package com.TCC.banco_de_ideias_back.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "interesses")
public class Interesse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long alunoId;

    @Column(nullable = false)
    private Long ideiaId;

    @Column(nullable = false)
    private LocalDateTime dataInteresse = LocalDateTime.now();

    public Interesse() {}

    public Interesse(Long alunoId, Long ideiaId) {
        this.alunoId = alunoId;
        this.ideiaId = ideiaId;
        this.dataInteresse = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getIdeiaId() {
        return ideiaId;
    }

    public void setIdeiaId(Long ideiaId) {
        this.ideiaId = ideiaId;
    }

    public LocalDateTime getDataInteresse() {
        return dataInteresse;
    }

    public void setDataInteresse(LocalDateTime dataInteresse) {
        this.dataInteresse = dataInteresse;
    }
}
