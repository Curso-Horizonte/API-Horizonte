package org.example.apihorizonte.dto.professorDisciplina;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.example.apihorizonte.validation.OnCreate;

@Getter
@Setter
public class ProfessorDisciplinaRequestDTO {
    @NotNull(groups = OnCreate.class)
    @Positive
    private Long professorId;

    @NotNull(groups = OnCreate.class)
    @Positive
    private Long disciplinaId;
}
