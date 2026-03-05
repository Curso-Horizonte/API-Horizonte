package org.example.apihorizonte.repository;

import org.example.apihorizonte.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    @Query("""
    SELECT n FROM Nota n
    JOIN FETCH n.alunoDisciplina ad
    JOIN FETCH ad.aluno
    JOIN FETCH ad.disciplina
    JOIN FETCH n.professor
    """)
    List<Nota> findAllWithRelations();

    @Query("""
    SELECT n FROM Nota n
    JOIN FETCH n.alunoDisciplina ad
    JOIN FETCH ad.aluno a
    JOIN FETCH ad.disciplina d
    JOIN FETCH n.professor
    WHERE a.id = :alunoId
    AND d.id = :disciplinaId
      x""")
    List<Nota> findNotasByAlunoAndDisciplina(Long alunoId, Long disciplinaId);
}
