package com.TCC.banco_de_ideias_back.service;

import com.TCC.banco_de_ideias_back.model.Aluno;
import com.TCC.banco_de_ideias_back.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private AlunoRepository repository;

    public AlunoService(AlunoRepository repository){ this.repository = repository; }

    public List<Aluno> listar(){ return repository.findAll(); }
}
