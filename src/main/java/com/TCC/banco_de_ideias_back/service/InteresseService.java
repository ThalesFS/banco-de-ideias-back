package com.TCC.banco_de_ideias_back.service;

import com.TCC.banco_de_ideias_back.model.*;
import com.TCC.banco_de_ideias_back.repository.InteresseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteresseService {

    private final InteresseRepository interesseRepository;

    public InteresseService(InteresseRepository interesseRepository) {
        this.interesseRepository = interesseRepository;
    }

    // Criar interesse
    public Interesse registrarInteresse(Long alunoId, Long ideiaId) {
        if (interesseRepository.existsByAlunoIdAndIdeiaId(alunoId, ideiaId)) {
            throw new RuntimeException("Você já demonstrou interesse nesta ideia.");
        }

        Interesse interesse = new Interesse(alunoId, ideiaId);
        return interesseRepository.save(interesse);
    }

    // Remover interesse
    public void removerInteresse(Long interesseId) {
        interesseRepository.deleteById(interesseId);
    }

    // Lista de interesses de um aluno
    public List<Interesse> listarPorAluno(Long alunoId) {
        return interesseRepository.findByAlunoId(alunoId);
    }

    // Lista de alunos interessados em uma ideia
    public List<Interesse> listarPorIdeia(Long ideiaId) {
        return interesseRepository.findByIdeiaId(ideiaId);
    }

    // Contador de interessados em uma ideia
    public long contarInteressados(Long ideiaId) {
        return interesseRepository.countByIdeiaId(ideiaId);
    }
}
