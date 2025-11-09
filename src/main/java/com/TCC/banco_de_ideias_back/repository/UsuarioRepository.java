package com.TCC.banco_de_ideias_back.repository;

import com.TCC.banco_de_ideias_back.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmailAndSenha(String email, String senha);
}
