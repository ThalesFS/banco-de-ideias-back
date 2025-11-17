package com.TCC.banco_de_ideias_back.controller;

import com.TCC.banco_de_ideias_back.dto.IdeiaDetalhesDTO;
import com.TCC.banco_de_ideias_back.dto.IdeiaListaDTO;
import com.TCC.banco_de_ideias_back.dto.IdeiaUpdateDTO;
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
    public Ideia salvar(@RequestBody Ideia ideia) {
        return service.salvar(ideia);
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
    public IdeiaDetalhesDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Ideia atualizar(@PathVariable Long id,
                           @RequestBody IdeiaUpdateDTO dto,
                           @RequestHeader("X-Professor-Id") Long professorId) {
        return service.atualizar(id, dto, professorId);
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
    public List<Ideia> listarPorProfessor(@PathVariable Long id) {
        return service.listarPorProfessor(id);
    }
}
