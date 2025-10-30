package com.TCC.banco_de_ideias_back.repository;

import com.TCC.banco_de_ideias_back.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
