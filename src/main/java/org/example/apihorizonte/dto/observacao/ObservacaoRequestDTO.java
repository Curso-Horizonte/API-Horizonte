package org.example.apihorizonte.dto.observacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.apihorizonte.validation.OnCreate;

@Getter
@Setter
public class ObservacaoRequestDTO {
    @NotNull(groups = OnCreate.class)
    @Positive
    private Long alunoId;

    @NotNull(groups = OnCreate.class)
    @Positive
    private Long professorId;

    @NotNull(groups = OnCreate.class)
    @Positive
    private Long disciplinaId;

    @NotBlank(groups = OnCreate.class)
    @Size(min = 5, max = 500)
    private String texto;
}