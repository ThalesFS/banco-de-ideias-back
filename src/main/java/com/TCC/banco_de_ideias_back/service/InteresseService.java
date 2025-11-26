package com.TCC.banco_de_ideias_back.service;

import com.TCC.banco_de_ideias_back.model.*;
import com.TCC.banco_de_ideias_back.repository.AlunoRepository;
import com.TCC.banco_de_ideias_back.repository.IdeiaRepository;
import com.TCC.banco_de_ideias_back.repository.InteresseRepository;
import com.TCC.banco_de_ideias_back.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteresseService {

    private final InteresseRepository interesseRepository;
    private final AlunoRepository alunoRepository;
    private final IdeiaRepository ideiaRepository;
    private final ProfessorRepository professorRepository;
    private final EmailService emailService;

    public InteresseService(InteresseRepository interesseRepository, AlunoRepository alunoRepository, IdeiaRepository ideiaRepository, ProfessorRepository professorRepository, EmailService emailService) {
        this.interesseRepository = interesseRepository;
        this.alunoRepository = alunoRepository;
        this.ideiaRepository = ideiaRepository;
        this.professorRepository = professorRepository;
        this.emailService = emailService;
    }

    // Criar interesse
    public Interesse registrarInteresse(Long alunoId, Long ideiaId) {
        if (interesseRepository.existsByAlunoIdAndIdeiaId(alunoId, ideiaId)) {
            throw new RuntimeException("Você já demonstrou interesse nesta ideia.");
        }

        // Buscar aluno
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Usuario usuarioAluno = aluno.getUsuario();

        // Buscar ideia
        Ideia ideia = ideiaRepository.findById(ideiaId)
                .orElseThrow(() -> new RuntimeException("Ideia não encontrada"));

        // Buscar professor dono da ideia
        Professor professor = professorRepository.findById(ideia.getProfessor().getId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Usuario usuarioProfessor = professor.getUsuario();

        // Gera o HTML do email
        String conteudoHtml = emailService.gerarEmailInteresse(
                usuarioProfessor.getNome(),
                usuarioAluno.getNome(),
                aluno.getCurso(),
                ideia.getTitulo(),
                ideia.getDescricao(),
                usuarioAluno.getEmail(),
                ideia.getId()
        );

        // Enviar email com o professor como destinatario, assunto e HTML adaptado
        emailService.enviarEmailHtml(usuarioProfessor.getEmail(),
                "Novo aluno interessado na sua ideia — ação recomendada",
                conteudoHtml
        );

        Interesse interesse = new Interesse(alunoId, ideiaId);
        return interesseRepository.save(interesse);
    }

    // Remover interesse
    public void removerInteresse(Long interesseId) {
        interesseRepository.deleteById(interesseId);
    }

    // Lista de interesses de um aluno
    public List<Interesse> listarPorAluno(Long alunoId) {
        return interesseRepository.findByAlunoId(alunoId);
    }

    // Lista de alunos interessados em uma ideia
    public List<Interesse> listarPorIdeia(Long ideiaId) {
        return interesseRepository.findByIdeiaId(ideiaId);
    }

    // Contador de interessados em uma ideia
    public long contarInteressados(Long ideiaId) {
        return interesseRepository.countByIdeiaId(ideiaId);
    }
}
