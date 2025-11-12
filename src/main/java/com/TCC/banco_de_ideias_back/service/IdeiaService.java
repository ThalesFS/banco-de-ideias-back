package com.TCC.banco_de_ideias_back.service;

import com.TCC.banco_de_ideias_back.model.Ideia;
import com.TCC.banco_de_ideias_back.repository.IdeiaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IdeiaService {

        private final IdeiaRepository repository;

        public IdeiaService(IdeiaRepository repository) {
            this.repository = repository;
        }

        public Ideia salvar(Ideia ideia) {
            return repository.save(ideia);
        }

        public List<Ideia> listar() {
            return repository.findAll();
        }

        public List<Ideia> listarPorProfessor(Long professorId) {
            return repository.findByProfessorId(professorId);
        }
}
