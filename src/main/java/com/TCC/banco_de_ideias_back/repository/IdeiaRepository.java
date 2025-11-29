package com.TCC.banco_de_ideias_back.repository;

import com.TCC.banco_de_ideias_back.model.Ideia;
import com.TCC.banco_de_ideias_back.model.StatusIdeia;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface IdeiaRepository extends JpaRepository<Ideia, Long> {
    List<Ideia> findByProfessor_Id(Long professorId);

    Page<Ideia> findByStatus(StatusIdeia statusIdeia, Pageable pageable);

    Page<Ideia> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    @Query("""
    SELECT i FROM Ideia i 
    JOIN i.professor p
    JOIN p.usuario u
    WHERE
        (:busca IS NULL OR
            LOWER(i.titulo) LIKE LOWER(CONCAT('%', :busca, '%')) OR
            LOWER(i.descricao) LIKE LOWER(CONCAT('%', :busca, '%')) OR
            LOWER(i.tecnologias) LIKE LOWER(CONCAT('%', :busca, '%')) OR
            LOWER(i.cursos) LIKE LOWER(CONCAT('%', :busca, '%')) OR
            LOWER(u.nome) LIKE LOWER(CONCAT('%', :busca, '%')) OR
            LOWER(p.departamento) LIKE LOWER(CONCAT('%', :busca, '%'))
        )
        AND (:status IS NULL OR i.status = :status)
    """)
    Page<Ideia> buscarAvancada(
            @Param("busca") String busca,
            @Param("status") StatusIdeia status,
            Pageable pageable
    );
}
