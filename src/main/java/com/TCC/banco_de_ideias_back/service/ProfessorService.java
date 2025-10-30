package com.TCC.banco_de_ideias_back.service;

import com.TCC.banco_de_ideias_back.model.Professor;
import com.TCC.banco_de_ideias_back.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository){
        this.repository = repository;
    }

    public List<Professor> listar(){
        return repository.findAll();
    }
}
