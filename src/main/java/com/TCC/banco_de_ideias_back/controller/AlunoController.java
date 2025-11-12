package com.TCC.banco_de_ideias_back.controller;

import com.TCC.banco_de_ideias_back.dto.AlunoDTO;
import com.TCC.banco_de_ideias_back.model.Aluno;
import com.TCC.banco_de_ideias_back.repository.AlunoRepository;
import com.TCC.banco_de_ideias_back.service.AlunoService;
import com.TCC.banco_de_ideias_back.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private AlunoService service;

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Aluno> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarPorId(@PathVariable Long id) {
        return alunoRepository.findById(id)
                .map(aluno -> ResponseEntity.ok(new AlunoDTO(aluno)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/senha")
    public ResponseEntity<?> atualizarSenha(@PathVariable Long id, @RequestBody Map<String, String> dados) {
        Aluno aluno = alunoRepository.findById(id).orElse(null);
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }

        String senhaAtual = dados.get("senhaAtual");
        String novaSenha = dados.get("novaSenha");

        boolean sucesso = usuarioService.atualizarSenha(aluno.getUsuario().getId(), senhaAtual, novaSenha);
        if (!sucesso) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Senha atual incorreta");
        }

        return ResponseEntity.ok("Senha atualizada com sucesso!");
    }
}
