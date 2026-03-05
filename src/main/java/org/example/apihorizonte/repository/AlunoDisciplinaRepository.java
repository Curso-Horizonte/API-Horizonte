package org.example.apihorizonte.repository;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.apihorizonte.model.AlunoDisciplina;
import org.example.apihorizonte.validation.OnCreate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoDisciplinaRepository extends JpaRepository<AlunoDisciplina, Long> {
    boolean existsByAlunoIdAndDisciplinaId(@NotNull(groups = OnCreate.class) @Positive Long alunoId, @NotNull(groups = OnCreate.class) @Positive Long disciplinaId);
}
