package org.example.apihorizonte.dto.alunoDisciplina;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.example.apihorizonte.validation.OnCreate;

@Getter
@Setter
public class AlunoDisciplinaRequestDTO {
    @NotNull(groups = OnCreate.class)
    @Positive
    private Long alunoId;

    @NotNull(groups = OnCreate.class)
    @Positive
    private Long disciplinaId;
}
