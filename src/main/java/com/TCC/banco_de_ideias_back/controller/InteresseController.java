package com.TCC.banco_de_ideias_back.controller;

import com.TCC.banco_de_ideias_back.dto.IdeiaListaInteresseDTO;
import com.TCC.banco_de_ideias_back.model.*;
import com.TCC.banco_de_ideias_back.dto.InteresseCriarDTO;
import com.TCC.banco_de_ideias_back.dto.AlunoInteressadoDTO;
import com.TCC.banco_de_ideias_back.repository.AlunoRepository;
import com.TCC.banco_de_ideias_back.repository.IdeiaRepository;
import com.TCC.banco_de_ideias_back.repository.InteresseRepository;
import com.TCC.banco_de_ideias_back.service.InteresseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/interesses")
public class InteresseController {

    private final InteresseService interesseService;
    private final AlunoRepository alunoRepository;
    private final InteresseRepository interesseRepository;
    private final IdeiaRepository ideiaRepository;

    public InteresseController(InteresseService interesseService, AlunoRepository alunoRepository, InteresseRepository interesseRepository, IdeiaRepository ideiaRepository) {
        this.interesseService = interesseService;
        this.alunoRepository = alunoRepository;
        this.interesseRepository = interesseRepository;
        this.ideiaRepository = ideiaRepository;
    }
    // ================================================ ALUNO ================================================
    // Aluno registra interesse na ideia
    @PostMapping
    public ResponseEntity<?> criarInteresse(@RequestBody InteresseCriarDTO dto) {
        try {
            Interesse interesse = interesseService.registrarInteresse(dto.getAlunoId(), dto.getIdeiaId());
            return ResponseEntity.status(201).body(interesse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    // Aluno remove interesse na ideia
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerInteresse(@PathVariable Long id) {
        interesseService.removerInteresse(id);
        return ResponseEntity.noContent().build();
    }

    // Lista todos os interesses do aluno
    @GetMapping("/aluno/{alunoId}")
    public List<IdeiaListaInteresseDTO> listarPorAluno(@PathVariable Long alunoId) {
        List<Interesse> interesses = interesseService.listarPorAluno(alunoId);

        return interesses.stream().map(interesse -> {
            Ideia ideia = ideiaRepository.findById(interesse.getIdeiaId()).orElse(null);
            if (ideia==null)return null;
            return new IdeiaListaInteresseDTO(ideia.getId(), ideia.getTitulo());
        }).filter(dto -> dto != null).collect(Collectors.toList());
    }

    @GetMapping("/verificar")
    public ResponseEntity<?> verificar(@RequestParam Long alunoId, @RequestParam Long ideiaId){
        Boolean interesse = interesseRepository.existsByAlunoIdAndIdeiaId(alunoId, ideiaId);
        if (!interesse){
            return ResponseEntity.status(404).body("Interesse não encontrado");
        }
        return ResponseEntity.ok().build(); //status 200
    }

    // Retorna o interesse (com ID) de aluno + ideia
    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(
            @RequestParam Long alunoId,
            @RequestParam Long ideiaId) {

        Interesse interesse = interesseRepository.findByAlunoIdAndIdeiaId(alunoId, ideiaId);

        if (interesse == null) {
            return ResponseEntity.status(404).body("Interesse não encontrado");
        }

        return ResponseEntity.ok(interesse); // retorna o próprio interesse (incluindo o ID)
    }
    // ================================================ ALUNO ================================================


    // ================================================ PROFESSOR ================================================
    // Listar alunos interessados em uma ideia (somente o professor dono da ideia vai visualizar)
    @GetMapping("/ideia/{ideiaId}")
    public List<AlunoInteressadoDTO> listarPorIdeia(@PathVariable Long ideiaId) {

        List<Interesse> interesses = interesseRepository.findByIdeiaId(ideiaId);

        return interesses.stream().map(interesse -> {
            Aluno aluno = alunoRepository.findById(interesse.getAlunoId()).orElse(null);

            if (aluno == null) {
                return new AlunoInteressadoDTO(interesse.getAlunoId(), "Nao encontrado", "-", "-");
            }

            return new AlunoInteressadoDTO(
                    aluno.getId(),
                    aluno.getUsuario().getNome(),
                    aluno.getUsuario().getEmail(),
                    aluno.getCurso()
            );
        }).collect(Collectors.toList());
    }

    // Lista a quantidade de interessados em uma ideia (pra aparecer no "Minhas Ideias")
    @GetMapping("/ideia/{ideiaId}/count")
    public Long contar(@PathVariable Long ideiaId) {
        return interesseService.contarInteressados(ideiaId);
    }
    // ================================================ PROFESSOR ================================================
}
