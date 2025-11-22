package com.TCC.banco_de_ideias_back.controller;

import com.TCC.banco_de_ideias_back.dto.*;
import com.TCC.banco_de_ideias_back.model.Ideia;
import com.TCC.banco_de_ideias_back.model.StatusIdeia;
import com.TCC.banco_de_ideias_back.service.IdeiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/ideias")
public class IdeiaController {

    @Autowired
    private final IdeiaService service;

    public IdeiaController(IdeiaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<IdeiaResponseDTO> salvar(@RequestBody IdeiaCriacaoDTO dto) {
        IdeiaResponseDTO resposta = service.salvar(dto);
        return ResponseEntity.ok(resposta);
    }

    //GET ideias listadas (parametros possiveis: busca textual, status, pagina, quantidade de itens por pagina)
    @GetMapping
    public Page<IdeiaListaDTO> listar(@RequestParam(required = false)String busca,
                                      @RequestParam(required = false)StatusIdeia statusIdeia,
                                      @RequestParam(defaultValue = "0")int page,
                                      @RequestParam(defaultValue = "5")int size){
        return service.listarIdeias(busca, statusIdeia, page, size);
    }

    @GetMapping("/all")
    public List<Ideia> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdeiaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @RequestBody IdeiaUpdateDTO dto,
            @RequestHeader("X-Professor-Id") Long professorId
    ) {
        service.atualizar(id, dto, professorId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id,
            @RequestHeader("X-Professor-Id") Long professorId
    ) {
        service.deletar(id, professorId);
        return ResponseEntity.noContent().build();
    }

    //Lista todas as ideias de um professor pelo seu ID
    @GetMapping("/professor/{id}")
    public List<IdeiaListaDTO> listarPorProfessor(@PathVariable Long id) {
        return service.listarPorProfessor(id).stream().map(ideia -> new IdeiaListaDTO(
                ideia.getId(),
                ideia.getTitulo(),
                ideia.getDescricao(),
                ideia.getCursos(),
                ideia.getTecnologias(),
                ideia.getStatus(),
                ideia.getProfessor().getUsuario().getNome(),
                ideia.getProfessor().getDepartamento()
        )).toList();
    }
}
