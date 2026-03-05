package org.example.apihorizonte.repository;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.apihorizonte.model.Professor;
import org.example.apihorizonte.model.ProfessorDisciplina;
import org.example.apihorizonte.validation.OnCreate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProfessorDisciplinaRepository extends JpaRepository<ProfessorDisciplina, Long> {
    boolean existsByProfessorIdAndDisciplinaId(@NotNull(groups = OnCreate.class) @Positive Long professorId, @NotNull(groups = OnCreate.class) @Positive Long disciplinaId);

    List<ProfessorDisciplina> findAllByProfessorId();
}
