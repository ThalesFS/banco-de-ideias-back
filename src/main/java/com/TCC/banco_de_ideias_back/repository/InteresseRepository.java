package com.TCC.banco_de_ideias_back.repository;

import com.TCC.banco_de_ideias_back.model.Interesse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InteresseRepository extends JpaRepository<Interesse, Long> {

    List<Interesse> findByAlunoId(Long alunoId);
    List<Interesse> findByIdeiaId(Long ideiaId);
    Interesse findByAlunoIdAndIdeiaId(Long alunoId, Long ideiaId);

    boolean existsByAlunoIdAndIdeiaId(Long alunoId, Long ideiaId);

    long countByIdeiaId(Long ideiaId);
}
