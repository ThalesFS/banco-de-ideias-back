package com.TCC.banco_de_ideias_back.service;

import com.TCC.banco_de_ideias_back.dto.UsuarioCadastroDTO;
import com.TCC.banco_de_ideias_back.model.*;
import com.TCC.banco_de_ideias_back.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

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

    public boolean atualizarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if (usuarioOptional.isEmpty()) return false;

        Usuario usuario = usuarioOptional.get();

        if(!usuario.getSenha().equals(senhaAtual)){
            return false; //senhas nao bateram
        }
        usuario.setSenha(novaSenha);
        usuarioRepository.save(usuario);
        return true;
    }

}
