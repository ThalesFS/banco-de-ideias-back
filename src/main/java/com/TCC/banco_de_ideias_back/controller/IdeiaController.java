package com.TCC.banco_de_ideias_back.controller;

import com.TCC.banco_de_ideias_back.model.Ideia;
import com.TCC.banco_de_ideias_back.service.IdeiaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/ideias")
public class IdeiaController {

    private final IdeiaService service;

    public IdeiaController(IdeiaService service) {
        this.service = service;
    }

    @PostMapping
    public Ideia salvar(@RequestBody Ideia ideia) {
        return service.salvar(ideia);
    }

    @GetMapping
    public List<Ideia> listar() {
        return service.listar();
    }

    //Lista todas as ideias de um professor pelo seu ID
    @GetMapping("/professor/{id}")
    public List<Ideia> listarPorProfessor(@PathVariable Long id) {
        return service.listarPorProfessor(id);
    }
}
