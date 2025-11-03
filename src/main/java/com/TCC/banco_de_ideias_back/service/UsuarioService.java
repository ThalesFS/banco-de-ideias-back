package com.TCC.banco_de_ideias_back.service;

import com.TCC.banco_de_ideias_back.dto.UsuarioCadastroDTO;
import com.TCC.banco_de_ideias_back.model.*;
import com.TCC.banco_de_ideias_back.repository.*;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }
    public Usuario cadastrar(UsuarioCadastroDTO dto){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome);
        usuario.setEmail(dto.email);
        usuario.setMatricula(dto.matricula);
        usuario.setSenha(dto.senha);
        usuario.setTipo(Usuario.TipoUsuario.valueOf(dto.tipo));

        usuario = usuarioRepository.save(usuario);

        if (dto.tipo.equals("aluno")) {
            Aluno aluno = new Aluno();
            aluno.setUsuario(usuario);
            aluno.setCurso(dto.curso);
            alunoRepository.save(aluno);
        } else {
            Professor professor = new Professor();
            professor.setUsuario(usuario);
            professor.setDepartamento(dto.departamento);
            professorRepository.save(professor);
        }

        return usuario;
    }

}
