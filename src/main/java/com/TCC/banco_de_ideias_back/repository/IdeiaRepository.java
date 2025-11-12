package com.TCC.banco_de_ideias_back.repository;

import com.TCC.banco_de_ideias_back.model.Ideia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdeiaRepository extends JpaRepository<Ideia, Long> {
    List<Ideia> findByProfessorId(Long professorId);
}
