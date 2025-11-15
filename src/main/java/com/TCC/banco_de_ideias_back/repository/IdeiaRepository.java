package com.TCC.banco_de_ideias_back.repository;

import com.TCC.banco_de_ideias_back.model.Ideia;
import com.TCC.banco_de_ideias_back.model.StatusIdeia;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IdeiaRepository extends JpaRepository<Ideia, Long> {
    List<Ideia> findByProfessorId(Long professorId);

    Page<Ideia> findByStatus(StatusIdeia statusIdeia, Pageable pageable);

    Page<Ideia> findByTituloIgnoringCase(String titulo, Pageable pageable);
}
