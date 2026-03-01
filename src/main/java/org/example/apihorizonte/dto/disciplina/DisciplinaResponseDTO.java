package org.example.apihorizonte.dto.disciplina;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisciplinaResponseDTO {
    private Long id;
    private String nome;
}
