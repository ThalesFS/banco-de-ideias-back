package com.TCC.banco_de_ideias_back.controller;

import com.TCC.banco_de_ideias_back.model.Professor;
import com.TCC.banco_de_ideias_back.service.ProfessorService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private ProfessorService service;

    public ProfessorController(ProfessorService service){
        this.service = service;
    }

    @GetMapping
    public List<Professor> listar(){
        return service.listar();
    }


    /*@GetMapping
    public List<String> listarProfessores() {
        return List.of("Deno", "Thales", "Miron");
    }*/

    /*@PostMapping
    public String adicionar(@RequestBody String nome){
        professores.add(nome);
        return "Professor adicionado: " + nome;
    }*/
}
