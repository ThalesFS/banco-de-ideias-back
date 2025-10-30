package com.TCC.banco_de_ideias_back.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private List<String> professores = new ArrayList<>(Arrays.asList("Deno", "Thales", "Miron"));

    @GetMapping
    public List<String> listr(){
        return professores;
    }

    @PostMapping
    public String adicionar(@RequestBody String nome){
        professores.add(nome);
        return "Professor adicionado: " + nome;
    }
}
