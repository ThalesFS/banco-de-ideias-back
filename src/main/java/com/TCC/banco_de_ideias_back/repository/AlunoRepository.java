package com.TCC.banco_de_ideias_back.repository;

import com.TCC.banco_de_ideias_back.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
