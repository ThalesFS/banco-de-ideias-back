package com.TCC.banco_de_ideias_back.controller;

import com.TCC.banco_de_ideias_back.dto.ProfessorDTO;
import com.TCC.banco_de_ideias_back.model.Professor;
import com.TCC.banco_de_ideias_back.repository.ProfessorRepository;
import com.TCC.banco_de_ideias_back.service.ProfessorService;
import com.TCC.banco_de_ideias_back.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private ProfessorService service;

    public ProfessorController(ProfessorService service, ProfessorRepository professorRepository){
        this.service = service;
        this.professorRepository = professorRepository;
    }

    @Autowired
    private final ProfessorRepository professorRepository;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Professor> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> buscarPorId(@PathVariable Long id) {
        return professorRepository.findById(id)
                .map(professor -> ResponseEntity.ok(new ProfessorDTO(professor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/senha")
    public ResponseEntity<?> atualizarSenha(@PathVariable Long id, @RequestBody Map<String, String> dados) {
        Professor professor = professorRepository.findById(id).orElse(null);
        if (professor == null) {
            return ResponseEntity.notFound().build();
        }

        String senhaAtual = dados.get("senhaAtual");
        String novaSenha = dados.get("novaSenha");

        boolean sucesso = usuarioService.atualizarSenha(professor.getUsuario().getId(), senhaAtual, novaSenha);
        if (!sucesso) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha atual incorreta");
        }
        return ResponseEntity.ok("Senha atualizada com sucesso!");
    }
}
