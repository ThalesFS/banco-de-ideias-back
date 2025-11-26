package com.TCC.banco_de_ideias_back.service;

import com.TCC.banco_de_ideias_back.dto.*;
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

    public class NotFoundException extends RuntimeException {
        public NotFoundException(String msg) { super(msg); }
    }
    public class ForbiddenException extends RuntimeException {
        public ForbiddenException(String msg) { super(msg); }
    }

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
            Professor professor = ideia.getProfessor();
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

    public IdeiaResponseDTO buscarPorId(Long id) {
        Ideia ideia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ideia não encontrada"));

        return new IdeiaResponseDTO(ideia);
    }

    public IdeiaResponseDTO salvar(IdeiaCriacaoDTO dto) {

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Ideia ideia = new Ideia();
        ideia.setProfessor(professor);
        ideia.setTitulo(dto.getTitulo());
        ideia.setDescricao(dto.getDescricao());
        ideia.setCursos(dto.getCursos());
        ideia.setTecnologias(dto.getTecnologias());

        Ideia salva = repository.save(ideia);

        return new IdeiaResponseDTO(salva);
    }

    public void atualizar(Long id, IdeiaUpdateDTO dto, Long professorId) {
        Ideia ideia = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ideia não encontrada."));

        if (!ideia.getProfessor().getId().equals(professorId)) {
            throw new ForbiddenException("Acesso negado! Somente o dono da ideia pode atualiza-la.");
        }

        ideia.setTitulo(dto.getTitulo());
        ideia.setDescricao(dto.getDescricao());
        ideia.setTecnologias(dto.getTecnologias());
        ideia.setCursos(dto.getCursos());
        ideia.setStatus(dto.getStatus());

        repository.save(ideia);
    }

    public void deletar(Long id, Long professorId) {
        Ideia ideia = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ideia não encontrada"));

        if (!ideia.getProfessor().getId().equals(professorId)) {
            throw new ForbiddenException("Acesso negado! Somente o dono da ideia pode atualiza-la.");
        }
        repository.deleteById(id);
    }



    public List<Ideia> listar() {
        return repository.findAll();
    }

    public List<Ideia> listarPorProfessor(Long professorId) {
        return repository.findByProfessor_Id(professorId);
    }
}
