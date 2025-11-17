package com.TCC.banco_de_ideias_back.service;

import com.TCC.banco_de_ideias_back.dto.IdeiaDetalhesDTO;
import com.TCC.banco_de_ideias_back.dto.IdeiaListaDTO;
import com.TCC.banco_de_ideias_back.dto.IdeiaUpdateDTO;
import com.TCC.banco_de_ideias_back.model.Ideia;
import com.TCC.banco_de_ideias_back.model.Professor;
import com.TCC.banco_de_ideias_back.model.StatusIdeia;
import com.TCC.banco_de_ideias_back.repository.IdeiaRepository;
import com.TCC.banco_de_ideias_back.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class IdeiaService {

    @Autowired
    private final IdeiaRepository repository;
    @Autowired
    private final ProfessorRepository professorRepository;

    public Page<IdeiaListaDTO> listarIdeias(String busca, StatusIdeia statusIdeia, int page, int size){

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<Ideia> resultados;

        if (busca != null && !busca.isEmpty()) {
            resultados = repository.findByTituloContainingIgnoreCase(busca, pageable);
        } else if (statusIdeia != null) {
            resultados = repository.findByStatus(statusIdeia, pageable);
        } else {
            resultados = repository.findAll(pageable);
        }
        return resultados.map(ideia -> {
            Professor professor = professorRepository.findById(ideia.getProfessorId()).orElse(null);
            return  new IdeiaListaDTO(
                    ideia.getId(),
                    ideia.getTitulo(),
                    ideia.getDescricao(),
                    ideia.getCursos(),
                    ideia.getTecnologias(),
                    ideia.getStatus(),
                    professor != null ? professor.getUsuario().getNome():"Desconhecido",
                    professor != null ? professor.getDepartamento():"-");
        });
    }

    public IdeiaService(IdeiaRepository repository, ProfessorRepository professorRepository) {
        this.repository = repository;
        this.professorRepository = professorRepository;
    }

    public IdeiaDetalhesDTO buscarPorId(Long id) {
        Ideia ideia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ideia não encontrada"));

        Professor professor = professorRepository.findById(ideia.getProfessorId()).orElse(null);

        return new IdeiaDetalhesDTO(
                ideia.getId(),
                ideia.getTitulo(),
                ideia.getDescricao(),
                ideia.getCursos(),
                ideia.getTecnologias(),
                ideia.getStatus(),
                professor != null ? professor.getUsuario().getNome() : "Desconhecido",
                professor != null ? professor.getDepartamento() : "-",
                professor != null ? professor.getUsuario().getEmail() : "-"
        );
    }

    public Ideia salvar(Ideia ideia) {
        return repository.save(ideia);
    }

    public Ideia atualizar(Long id, IdeiaUpdateDTO dto, Long professorId) {
        Ideia ideia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ideia não encontrada"));

        // validao o professor original da ideia para evitar alteração indevida
        if (!ideia.getProfessorId().equals(professorId)) {
            throw new RuntimeException("Acesso negado! Somente o dono da ideia pode atualiza-la.");
        }

        ideia.setTitulo(dto.getTitulo());
        ideia.setDescricao(dto.getDescricao());
        ideia.setTecnologias(dto.getTecnologias());
        ideia.setCursos(dto.getCursos());
        ideia.setStatus(dto.getStatus());
        return repository.save(ideia);
    }

    public void deletar(Long id, Long professorId) {
        Ideia ideia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ideia não encontrada"));

        if (!ideia.getProfessorId().equals(professorId)) {
            throw new RuntimeException("Acesso negado! Somente o dono da ideia pode atualiza-la.");
        }
        repository.deleteById(id);
    }



    public List<Ideia> listar() {
        return repository.findAll();
    }

    public List<Ideia> listarPorProfessor(Long professorId) {
        return repository.findByProfessorId(professorId);
    }
}
